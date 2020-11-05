
<template>
  <div class="nut-tab-part">
    <div class="nut-tab" :class="{ 'nut-tab-horizontal': positionNavCss }">
      <div
        v-if="positionNav == 'right' || positionNav == 'bottom'"
        class="nut-tab-item"
        ref="items"
      >
        <slot></slot>
      </div>
      <div :class="titleClass" ref="navlist" :style="customHeight">
        <b v-if="isShowLine" :class="navBarClass" :style="navBarStyle"></b>
        <span
          v-for="(value, index) in tabTitleList"
          :key="index"
          :class="[titleNavList, 'nut-title-nav', { 'nut-tab-disable': value.disable }, { 'nut-tab-active': activeIndex == index }]"
        >
          <a class="nut-tab-link" v-on:click="switchTab(index, $event, value.disable)">
            <i
              class="nut-tab-icon"
              :style="{ backgroundImage: 'url(' + value.iconUrl + ')' }"
              v-if="value.iconUrl"
            ></i>
            {{ value.tabTitle }}
          </a>
        </span>
      </div>
      <div
        v-if="positionNav == 'top' || positionNav == 'left'"
        class="nut-tab-item"
        ref="items"
        :style="customHeight"
      >
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "nut-tab",
  props: {
    isScroll: {
      type: Boolean,
      default: false
    },
    isShowLine: {
      type: Boolean,
      default: true
    },
    defIndex: {
      type: Number,
      default: 0
    },
    positionNav: {
      type: String,
      default: "top"
    },
    initData: {
      type: Array,
      default: function() {
        return [];
      }
    },
    wrapperHeight: {
      type: [String, Number],
      default: "200"
    },
    lineWidth: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      tabTitleList: [],
      activeIndex: this.defIndex,
      initX: "0",
      navWidth: 0,
      tapWidth: 0
    };
  },
  watch: {
    defIndex() {
      this.updeteTab();
    },
    initData: {
      handler() {
        this.updeteTab();
      },
      deep: true
    }
  },
  computed: {
    //下面有些样式名称是为了兼容之前的版本
    positionNavCss: function() {
      if (this.positionNav === "left" || this.positionNav === "right")
        return true;
    },
    titleClass: function() {
      if (this.positionNav == "top") {
        return "nut-tab-title";
      }
      return "nut-tab-title-" + this.positionNav + "nav";
    },
    navBarClass: function() {
      if (this.positionNav == "top") {
        return "nav-bar";
      }
      return "nav-bar-" + this.positionNav;
    },
    titleNavList: function() {
      if (this.positionNav == "top" || this.positionNav == "bottom") {
        if (this.isScroll) {
          return "nut-title-nav-scroll";
        }
        return "nut-title-nav-list";
      } else {
        if (this.isScroll) {
          return "nut-title-vertical-scroll";
        }
        return "nut-title-nav-" + this.positionNav + "nav";
      }
    },
    navBarStyle: function() {
      if (this.positionNav === "top" || this.positionNav === "bottom") {
        return {
          transform: `translateX(${this.initX}px)`,
          width: this.lineWidth + "px"
          // width: this.navWidth + "px"//修改公式，以增加需求
        };
      }
      return {
        transform: `translateY(${this.initX}px)`,
        height: this.lineWidth + "px"
        // height: this.navWidth + "px",
      };
    },
    customHeight: function() {
      if (
        this.isScroll &&
        (this.positionNav === "left" || this.positionNav === "right")
      ) {
        return {
          height: this.wrapperHeight + "px"
        };
      } else {
        return null;
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.$slots.default && this.updeteTab(this.$slots.default);
    });
  },
  methods: {
    updeteTab: function() {
      this.$nextTick(() => {
        this.tabTitleList = [];
        this.activeIndex = this.defIndex;
        this.initTab([...this.$slots.default]);
      });
    },
    initTab: function(slot) {
      for (let i = 0; i < slot.length; i++) {
        let slotTag = slot[i].tag;
        if (
          typeof slotTag == "string" &&
          slotTag.indexOf("nut-tab-panel") != -1
        ) {
          let attrs = slot[i].data.attrs;
          let item = {
            tabTitle: attrs["tab-title"] || attrs["tabTitle"],
            disable: attrs.disable === false,
            iconUrl: attrs["iconUrl"] || attrs["icon-url"]
          };
          this.tabTitleList.push(item);
          let slotElm = slot[i].elm;
          if (slotElm) {
            slotElm.classList.add("hide");
            if (this.activeIndex == i) {
              slotElm.classList.remove("hide");
            }
          }
        }
      }
      this.$nextTick(() => {
        let tapWidth;
        if (this.positionNav == "top" || this.positionNav == "bottom") {
          this.navWidth = this.$refs.navlist.querySelector(
            ".nut-title-nav"
          ).offsetWidth;
          tapWidth = this.$refs.navlist.offsetWidth;
        } else {
          this.navWidth = this.$refs.navlist.querySelector(
            ".nut-title-nav"
          ).offsetHeight;
          tapWidth = this.$refs.navlist.offsetHeight;
        }
        this.initX = parseInt(this.navWidth * this.defIndex);

        if (this.lineWidth > 0 && this.lineWidth < this.navWidth) {
          this.initX = parseInt(
            (this.navWidth - this.lineWidth) / 2 + this.navWidth * this.defIndex
          );
        }

        this.tapWidth = tapWidth / 2 - this.navWidth / 2;
      });
    },
    findParent(event, myclass) {
      let parentCpt = event.target;
      let flag = 0; //避免死循环
      while (parentCpt && flag < 10) {
        flag++;
        if (parentCpt.className && parentCpt.className === myclass) {
          break;
        }
        parentCpt = parentCpt.parentNode;
      }
      return parentCpt;
    },
    switchTab: function(index, event, disable) {
      if (!disable) {
        this.activeIndex = index;
        this.initX = parseInt(this.navWidth * index);
        if (this.lineWidth > 0 && this.lineWidth < this.navWidth) {
          this.initX = parseInt(
            (this.navWidth - this.lineWidth) / 2 + this.navWidth * index
          );
        }

        if (this.positionNav == "top" || this.positionNav == "bottom") {
          this.$refs.navlist.scroll(this.initX - this.tapWidth, 0);
        } else {
          this.$refs.navlist.scroll(0, this.initX - this.tapWidth);
        }
        let nutTab = this.findParent(event, "nut-tab-part");
        let items = this.$refs.items.children;
        for (let i = 0; i < items.length; i++) {
          if (i == index) {
            items[i].classList.remove("hide");
          } else {
            items[i].classList.add("hide");
          }
        }
        this.$emit("tab-switch", index, event);
        this.$emit("tabSwitch", index, event); //兼容以前驼峰法命名
      }
    }
  }
};
</script>

<style lang="scss" scoped>
$font-size-small: 12px;
$primary-color: #577afb;
.nut-tab {
  position: relative;
  // border: 1px solid #eee;
  // padding: 10px;
  font-size: 12px;
  background: #eee;
}
.nut-tab-horizontal {
  display: flex;
  flex-direction: row;
  position: relative;
}

.close-btn {
  position: absolute;
  width: 17px;
  height: 17px;
  text-align: center;
  line-height: 14px;
  font-size: 16px;
  background: #999;
  font-weight: normal;
  color: #fff;
  right: 0px;
  top: 0px;
  border-radius: 50%;
}
.nut-tab-horizontal .close-btn {
  position: absolute;
  width: 17px;
  height: 17px;
  text-align: center;
  line-height: 14px;
  font-size: 16px;
  background: #999;
  font-weight: normal;
  color: #fff;
  right: 0px;
  top: 0px;
  border-radius: 50%;
}

.nut-tab-title:last-child {
  background-color: #000;
}
.nut-tab-title {
  //border:1px solid #fff;
  // border-bottom: 1px solid #ededed;
  scroll-behavior: smooth;
  width: 100%;
  display: flex;
  // height: 50px;
  // line-height: 48px;
  box-sizing: border-box;
  position: relative;
  overflow-x: auto;
  overflow-y: hidden;

  border-bottom: unset;
  height: 46px;
  line-height: 46px;
  &::-webkit-scrollbar {
    display: none;
  }
}
.nav-bar {
  position: absolute;
  height: 2px;
  bottom: 0px;
  left: 0px;
  z-index: 2;
  background: $primary-color;
  transition: all 0.3s ease-in-out;
}
.nut-tab-title-leftnav {
  border-right: 1px solid #ededed;
  scroll-behavior: smooth;
  width: 100px;
  display: flex;
  background: #fff;
  flex-direction: column;
  position: relative;
  // height: 200px;
  overflow-y: auto;
  overflow-x: hidden;
  &::-webkit-scrollbar {
    display: none;
  }
  .nav-bar-left {
    position: absolute;
    width: 2px;
    right: 0px;
    top: 0px;
    z-index: 2;
    background: $primary-color;
    transition: all 0.3s ease-in-out;
  }
  .nut-title-nav {
    border: 0;
    border-left: 1px solid #f5f7fa;
  }
  .nut-tab-active {
    background-color: #fff;

    background: #fff;
    a {
      // color: red;
      font-size: 17px;
      color: rgba(39, 40, 41, 1);
    }
  }
}
.nut-tab-title-rightnav {
  background: #fff;
  // border: 1px solid #fff;
  scroll-behavior: smooth;
  border-left: 1px solid #ededed;
  width: 100px;
  display: flex;
  flex-direction: column;
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  // height: 200px;
  box-sizing: border-box;
  &::-webkit-scrollbar {
    display: none;
  }
  .nav-bar-right {
    position: absolute;
    width: 2px;
    left: 0px;
    top: 0px;
    z-index: 2;
    background: $primary-color;
    transition: all 0.3s ease-in-out;
  }
}
.nut-tab-link {
  // color: #333;
  display: flex;
  align-items: center;
  justify-content: center;
  // font-size: 12px;
  text-decoration: none;
  line-height: 1;
  width: 100%;
  height: 100%;

  font-size: 14px;
  color: rgba(101, 102, 103, 1);
}
.nut-tab-title-bottomnav {
  border: 1px solid #fff;
  scroll-behavior: smooth;
  border-top: 1px solid #ededed;
  width: 100%;
  display: flex;
  height: 50px;
  line-height: 49px;
  box-sizing: border-box;
  position: relative;
  overflow-x: auto;
  overflow-y: hidden;
  &::-webkit-scrollbar {
    display: none;
  }
  .nav-bar-bottom {
    position: absolute;
    height: 2px;
    left: 0px;
    top: 0px;
    z-index: 2;
    background: $primary-color;
    transition: all 0.3s ease-in-out;
  }
}

.nut-title-nav-list {
  flex: 1;
  position: relative;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  display: flex;
  background-color: #fff;

  background: #fff;
  box-sizing: border-box;
}
.nut-title-nav-leftnav {
  flex: 1;
  display: flex;
  padding-left: 5px;
  //justify-content: center;
  align-items: center;
  position: relative;
}
.nut-title-nav-rightnav {
  flex: 1;
  display: flex;
  padding-right: 5px;
  justify-content: flex-end;
  align-items: center;
  position: relative;
}
.nut-title-nav-scroll {
  // min-width: 100px;
  min-width: calc(100% / 6);

  position: relative;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  display: flex;
  background: #fff;
  box-sizing: border-box;
}
.nut-title-vertical-scroll {
  min-height: 55px;
  position: relative;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  display: flex;
  background: #fff;
  box-sizing: border-box;
}
.nut-tab-icon {
  display: inline-block;
  margin-right: 5px;
  width: 20px;
  height: 20px;
  background-repeat: no-repeat;
  background-size: 100% 100%;
}
.nut-tab-active {
  background: #fff;
  border: 0;
  a {
    // color: red;
    font-size: 17px;
    color: #577afb;
  }
}
.nut-tab-item {
  // height: 200px;
  // border: 1px solid #fff;
  background: #fff;
  width: 100%;
  // padding: 10px;
  box-sizing: border-box;
  .hide {
    display: none;
  }
}
.nut-tab-disable {
  background: #e1e1e1 !important;
}
.tabbar-nav-word {
  font-size: $font-size-small;
}
</style>