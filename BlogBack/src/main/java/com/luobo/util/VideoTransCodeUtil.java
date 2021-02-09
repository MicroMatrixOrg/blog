package com.luobo.util;

import com.luobo.entity.FileMessage;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.*;


import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class VideoTransCodeUtil {

    public static File judgeFile(String videoPath, MultipartFile file) throws IOException, EmpryFileException {
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {             // 视频转属性
           throw new EmpryFileException("文件为空");
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(videoPath + file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    public static FileMessage VideoTransCode(String videoPath, MultipartFile file) throws Exception {
        File filePath = new File(videoPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        File toFile = judgeFile(videoPath, file);
        FileMessage message = new FileMessage();
        int index = toFile.getName().lastIndexOf(".");
        String name = "";
        if (index > 0)
            name = toFile.getName().substring(0, index);

        String fileName = UUID.randomUUID().toString();

        // 获取视屏长度
        MultimediaObject instance = new MultimediaObject(toFile);
        MultimediaInfo result = instance.getInfo();
        long ls = result.getDuration() / 2000;
        System.out.println("时长："+result.getDuration());
        float x = ls;

        /*FileInputStream fis = new FileInputStream(toFile);

        FileChannel fc = fis.getChannel();

        BigDecimal fileSize = new BigDecimal(fc.size());
        //视频大小
        String size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
*/



        VideoInfo video = result.getVideo();
        AudioInfo audio = result.getAudio();
        if (video != null) {
            String decoder = video.getDecoder();
            try {
                getVideoPicture(videoPath, fileName, toFile, x);
            } catch (EncoderException e) {
                getVideoPicture(videoPath, fileName, toFile, 2);//第2s的时候截图
                e.printStackTrace();
            }
            // 视频截图
            File picture = new File(videoPath + fileName + ".jpg");
            message = getImgWidthHeight(picture);
            if (decoder.startsWith("h264")) {       // 判断是不是h264格式
                toFile.renameTo(new File(videoPath + fileName + ".mp4"));
            } else {
                ChangeVideo changeVideo = new ChangeVideo(videoPath, fileName, toFile);// 转换视频格式
                changeVideo.start();
            }
        }
        if(audio!=null){//音频转码
            String decoder = audio.getDecoder();

            if (decoder.startsWith("mp3")) {
                toFile.renameTo(new File(videoPath + fileName + ".mp3"));
            } else {
                ChangeAudio changeVideo = new ChangeAudio(videoPath, fileName, toFile);// 转换视频格式
                changeVideo.start();
            }
        }

            message.setFileName(fileName);
            message.setVideoSize(result.getDuration() / 1000);
            //  message.setSize(size);
            return message;

        }
    /**
     * 获取图片宽 高度
     *
     * @param file 图片文件
     */
    public static FileMessage getImgWidthHeight(File file) {
        InputStream is = null;
        BufferedImage src = null;
        FileMessage fileMessage = new FileMessage();
        try {
            is = new FileInputStream(file);
            src = javax.imageio.ImageIO.read(is);
            int height = src.getHeight(null);// 得到源图高
            int width = src.getWidth(null);// 得到源图宽
            fileMessage.setImgHeight(height);
            fileMessage.setImgWidth(width);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileMessage;
    }

    // 转换视频格式
    public static void changeVideoType(String videoPath, String fileName, File toFile) throws EncoderException {
        File target = new File(videoPath + fileName + ".mp4");
        // 视频转码
        if (target.exists()) {
            target.delete();
        }
        AudioAttributes audioAttr = new AudioAttributes();
        VideoAttributes videoAttr = new VideoAttributes();
        EncodingAttributes encodingAttr = new EncodingAttributes();

        audioAttr.setChannels(2);
        audioAttr.setCodec("aac");
        //或者    audioAttr.setCodec("libmp3lame");       建议aac    此行的编码格式可能会造成意想不到的错误
        audioAttr.setBitRate(128000);
        audioAttr.setSamplingRate(44100);

        videoAttr.setCodec("libx264");
        videoAttr.setBitRate(4000000);

        encodingAttr.setAudioAttributes(audioAttr);
        encodingAttr.setVideoAttributes(videoAttr);
        encodingAttr.setFormat("mp4");

        Encoder encoder = new Encoder();
        encoder.encode(new MultimediaObject(toFile), target, encodingAttr);
    }

    public static void getVideoPicture(String videoPath, String fileName, File toFile, float x) throws EncoderException {
        File picture = new File(videoPath + fileName + ".jpg");
        VideoAttributes videoPicture = new VideoAttributes();// 视频属性
        videoPicture.setCodec("mjpeg");// 图片编码
//        videoPicture.setSize(new VideoSize(1200, 800));// 设置图片宽高
        EncodingAttributes attrPicture = new EncodingAttributes();// 转码属性
        attrPicture.setFormat("image2");// 转码格式
        attrPicture.setOffset(x);// 设置偏移位置，即开始转码位置（3秒）
        attrPicture.setDuration(0.01f);// 设置转码持续时间（1秒）
        attrPicture.setVideoAttributes(videoPicture);

        Encoder encoderPicture = new Encoder();
        encoderPicture.encode(new MultimediaObject(toFile), picture, attrPicture);
    }

    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File source = new File("/Users/davidmorgan/Pictures/uploadFile/blog/2021/1/31/output.mp4");
        MultimediaObject multimediaObject = new MultimediaObject(source);
        System.out.println(multimediaObject);

    }

}

class ChangeVideo extends Thread {
    private Thread t;
    private String videoPath;
    private String fileName;
    private File toFile;

    public ChangeVideo(String videoPath, String fileName, File toFile) {
        this.videoPath = videoPath;
        this.fileName = fileName;
        this.toFile = toFile;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        File target = new File(videoPath + fileName + ".mp4");
        // 视频转码
        if (target.exists()) {
            target.delete();
        }
        AudioAttributes audioAttr = new AudioAttributes();
        VideoAttributes videoAttr = new VideoAttributes();
        EncodingAttributes encodingAttr = new EncodingAttributes();

        audioAttr.setChannels(2);
        audioAttr.setCodec("aac");
        //或者    audioAttr.setCodec("libmp3lame");       建议aac    此行的编码格式可能会造成意想不到的错误
        audioAttr.setBitRate(128000);
        audioAttr.setSamplingRate(44100);

        videoAttr.setCodec("libx264");
        videoAttr.setBitRate(4000000);

        encodingAttr.setAudioAttributes(audioAttr);
        encodingAttr.setVideoAttributes(videoAttr);
        encodingAttr.setFormat("mp4");

        Encoder encoder = new Encoder();
        try {
            encoder.encode(new MultimediaObject(toFile), target, encodingAttr);
            if (toFile.exists()) {
                toFile.delete();
            }
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}

class ChangeAudio extends Thread {
    private Thread t;
    private String videoPath;
    private String fileName;
    private File toFile;

    public ChangeAudio(String videoPath, String fileName, File toFile) {
        this.videoPath = videoPath;
        this.fileName = fileName;
        this.toFile = toFile;
    }

    public void start() {
        if (t == null) {
            t = new Thread(this);
            t.start();
        }
    }

    @Override
    public void run() {
        File target = new File(videoPath + fileName + ".mp3");
        // 音频转码
        if (target.exists()) {
            target.delete();
        }

        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try {
            if (toFile.exists()) {
                encoder.encode(new MultimediaObject(toFile), target, attrs);
                toFile.delete();
            }
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

}
