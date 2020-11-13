<!--
 * @Author: David
 * @Date: 2020-11-11 17:39:52
 * @LastEditTime: 2020-11-12 16:34:51
 * @LastEditors: David
 * @Description: 编辑输入框
 * @FilePath: /BlogVue/src/components/input/EditInput.vue
 * @可以输入预定的版权声明、个性签名、空行等
-->
<template>
  <div class="input-box">
    <input
      :id="`input${time}`"
      :value="inputValue"
      spellcheck="false"
      :placeholder="placeholder"
      class="input"
      @focus="inputChose()"
    />
    <div class="action-box">
      <button
        style=""
        class="btn edit-btn"
        tabindex="-1"
        @click="edit()"
        :id="`editbtn${time}`"
      >
        <i class="fa fa-pencil" aria-hidden="true"></i>
        <span>修改</span>
      </button>
      <button
        :id="`savebtn${time}`"
        style="display:none;"
        class="btn edit-btn confirm-btn"
        tabindex="-1"
        @click="save()"
      >
        保存
      </button>
      <button
        :id="`canclebtn${time}`"
        style="display:none; color:#000;"
        class="btn edit-btn confirm-btn"
        tabindex="-1"
        @click="cancle()"
      >
        取消
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    placeholder: {
      type: String,
      default: ""
    },
    inputValue: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      time: new Date().getTime()
    };
  },
  mounted() {},
  methods: {
    /**
     * @description: input框聚焦
     * @Date: 2020-11-12 11:11:39
     * @Author: David
     */

    inputChose() {
      this.showbtn();
    },
    /**
     * @description: 编辑操作按钮
     * @Date: 2020-11-12 11:03:16
     * @Author: David
     */

    edit() {
      this.showbtn();
    },
    /**
     * @description: 保存操作按钮
     * @Date: 2020-11-12 11:03:26
     * @Author: David
     */

    save() {
      const input = document.getElementById(`input${this.time}`);
      this.$emit("inputVale", input.value);
      this.showbtn();
    },
    /**
     * @description: 取消操作按钮
     * @Date: 2020-11-12 11:03:38
     * @Author: David
     */

    cancle() {
      const input = document.getElementById(`input${this.time}`);
      input.value = this.inputValue;
      this.showbtn();
    },

    showbtn() {
      const edit = document.getElementById(`editbtn${this.time}`);
      const save = document.getElementById(`savebtn${this.time}`);
      const cancle = document.getElementById(`canclebtn${this.time}`);
      this.$utils.hmst(edit, save, cancle);
    }
  }
};
</script>

<style lang="scss" scoped>
.input {
  flex: 1;
  display: inline-block;
  border: none;
  outline: none;
  color: #909090;
  font-size: 1.3rem;
}
.input-box {
  display: flex;
  justify-content: flex-end;
  flex: 1;
}
.action-box {
  margin-left: 1rem;
}
.btn {
  background-color: transparent;
  color: #007fff;
  font-size: 1.2rem;
  padding: 0;
  white-space: nowrap;
}
.edit-btn {
  appearance: none;

  border-radius: 2px;
  border: none;
  padding: 0.5rem 1.3rem;
  outline: none;
  transition: background-color 0.3s, color 0.3s;
  cursor: pointer;
}
.confirm-btn {
  margin-right: 10px;
}
</style>
