<template>
  <b-row>
    <canvas ref="myCanvas" :width="contentWidth" :height="contentHeight"></canvas>
  </b-row>
</template>

<script lang="ts">
import { onMounted, ref, watch } from '@vue/composition-api';
import { BRow } from 'bootstrap-vue';
export default {
  name: 'SIdentify',
  components: { BRow },
  props: {
    identifyCodeDefault: {
      //預設註冊碼
      type: String,
      default: '1234',
    },
    fontSizeMinDefault: {
      // 字型最小值
      type: Number,
      default: 25,
    },
    fontSizeMaxDefault: {
      // 字型最大值
      type: Number,
      default: 35,
    },
    backgroundColorMinDefault: {
      // 驗證碼圖片背景色最小值
      type: Number,
      default: 200,
    },
    backgroundColorMaxDefault: {
      // 驗證碼圖片背景色最大值
      type: Number,
      default: 220,
    },
    dotColorMinDefault: {
      // 背景干擾點最小值
      type: Number,
      default: 60,
    },
    dotColorMaxDefault: {
      // 背景干擾點最大值
      type: Number,
      default: 120,
    },
    contentWidthDefault: {
      //容器寬度
      type: Number,
      default: 116,
    },
    contentHeightDefault: {
      //容器高度
      type: Number,
      default: 38,
    },
  },

  setup(props) {
    let myCanvas = ref(null);
    onMounted(() => {
      drawPic();
    });

    const identifyCode = ref(props.identifyCodeDefault);
    const fontSizeMin = ref(props.fontSizeMinDefault);
    const fontSizeMax = ref(props.fontSizeMaxDefault);
    const backgroundColorMin = ref(props.backgroundColorMinDefault);
    const backgroundColorMax = ref(props.backgroundColorMaxDefault);
    const dotColorMin = ref(props.dotColorMinDefault);
    const dotColorMax = ref(props.dotColorMaxDefault);
    const contentWidth = ref(props.contentWidthDefault);
    const contentHeight = ref(props.contentHeightDefault);

    // 生成一個隨機數
    function randomNum(min, max) {
      return Math.floor(Math.random() * (max - min) + min);
    }
    // 生成一個隨機的顏色
    function randomColor(min, max) {
      let r = randomNum(min, max);
      let g = randomNum(min, max);
      let b = randomNum(min, max);
      return 'rgb(' + r + ',' + g + ',' + b + ')';
    }

    function drawPic() {
      let ctx = myCanvas.value.getContext('2d');
      ctx.textBaseline = 'bottom';
      // 繪製背景
      ctx.fillStyle = randomColor(backgroundColorMin.value, backgroundColorMax.value);
      ctx.fillRect(0, 0, contentWidth.value, contentHeight.value);
      // 繪製文字
      for (let i = 0; i < identifyCode.value.length; i++) {
        drawText(ctx, identifyCode.value[i], i);
      }
      drawLine(ctx);
      drawDot(ctx);
    }

    function drawText(ctx, txt, i) {
      ctx.fillStyle = randomColor(50, 160); //隨機生成字型顏色
      ctx.font = randomNum(fontSizeMin.value, fontSizeMax.value) + 'px SimHei'; //隨機生成字型大小
      let x = (i + 1) * (contentWidth.value / (identifyCode.value.length + 1));
      let y = randomNum(fontSizeMax.value, contentHeight.value - 5);
      var deg = randomNum(-30, 30);
      // 修改座標原點和旋轉角度
      ctx.translate(x, y);
      ctx.rotate((deg * Math.PI) / 180);
      ctx.fillText(txt, 0, 0);
      // 恢復座標原點和旋轉角度
      ctx.rotate((-deg * Math.PI) / 180);
      ctx.translate(-x, -y);
    }

    function drawLine(ctx) {
      // 繪製干擾線
      for (let i = 0; i < 4; i++) {
        ctx.strokeStyle = randomColor(100, 200);
        ctx.beginPath();
        ctx.moveTo(randomNum(0, contentWidth.value), randomNum(0, contentHeight.value));
        ctx.lineTo(randomNum(0, contentWidth.value), randomNum(0, contentHeight.value));
        ctx.stroke();
      }
    }

    function drawDot(ctx) {
      // 繪製干擾點
      for (let i = 0; i < 30; i++) {
        ctx.fillStyle = randomColor(0, 255);
        ctx.beginPath();
        ctx.arc(randomNum(0, contentWidth.value), randomNum(0, contentHeight.value), 1, 0, 2 * Math.PI);
        ctx.fill();
      }
    }

    watch(
      () => props.identifyCodeDefault,
      newValue => {
        identifyCode.value = newValue;
        drawPic();
      }
    );

    return {
      myCanvas,
      contentWidth,
      contentHeight,
    };
  },
};
</script>
