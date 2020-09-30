let PHOTOGRAPH = [".bmp", ".jpg", ".png", ".tif", ".gif", ".pcx", ".tga", ".exif", ".fpx", ".svg", ".psd", ".cdr", ".pcd", ".dxf", ".ufo", ".eps", ".ai", ".raw", ".WMF", ".webp"];
let WORD = [".doc", ".txt", ".docx", ".pdf", ".ppt", ".pptx", ".wps", ".docx"];
let EXCEL = [".xls", ".xlsx"];
let AUDIO = [".mp3", ".wav", ".flac", ".aac"];
let PICTURE = [".png", ".jpg", ".jpeg", ".webp"]
let VIDEO = ['.mp4', ".rmvb", ".3g2", ".3gp", ".aaf", ".asf", ".av1", ".avchd", ".cavs", ".divx", ".dv", ".f4v", ".flv", ".hevc", ".m2ts", ".m2v", ".m4v", ".mkv", ".mod", ".mov", ".mpeg", ".mpg", ".mts", ".mxf", ".ogv", ".rm", ".swf", ".tod", ".ts", ".vob", ".webm", ".wmv", ".wtv", ".xvid", ".avi",
]

var fileNameMatch = {
    matchName(fileName, matchType) {
        var fileType = fileName.substring(
            fileName.lastIndexOf("."),
            fileName.length
        );
        var temArr = [];
        for (var i = 0; i < matchType.length; i++) {
            if (matchType[i] == "photograph") {
                temArr = temArr.concat(PHOTOGRAPH);
            }
            if (matchType[i] == "doc") {
                temArr = temArr.concat(WORD);
            }
            if (matchType[i] == "xls") {
                temArr = temArr.concat(EXCEL);
            }
        }
        var temString = temArr.toString();
        return (temString.match(fileType) != null);
    },

    getAcceptType(matchType) {
        var temArr = [];
        for (var i = 0; i < matchType.length; i++) {
            if (matchType[i] == "photograph") {
                temArr = temArr.concat(PHOTOGRAPH);
            }
            if (matchType[i] == "doc") {
                temArr = temArr.concat(WORD);
            }
            if (matchType[i] == "xls") {
                temArr = temArr.concat(EXCEL);
            }
        }
        var temString = temArr.toString();
        return temString;
    },

    getFileType(fileName) {
        var fileType = fileName.substring(
            fileName.lastIndexOf("."),
            fileName.length
        );
        return fileType;
    },

    isPicture(fileName) {
        if (-1 != PICTURE.indexOf(fileNameMatch.getFileType(fileName))) {
            return true;
        } else {
            return false;
        }
    },

    isVideo(fileName) {
        if (-1 != VIDEO.indexOf(fileNameMatch.getFileType(fileName))) {
            return true;
        } else {
            return false;
        }
    },

    isAudio(fileName) {
        if (-1 != AUDIO.indexOf(fileNameMatch.getFileType(fileName))) {
            return true;
        } else {
            return false;
        }
    }
}

export default fileNameMatch;