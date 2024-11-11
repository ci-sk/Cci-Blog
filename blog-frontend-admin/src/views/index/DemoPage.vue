<script setup>
import {getLimitComment} from "../../net/comment.js";
import {changeTime} from "../../uilt/index.js";
import {ref} from "vue";


const input = ref('')

const page = ref(1)

const CommentInfo = ref([])


const SelectComment =()=> {
  getLimitComment({
    text:input.value,
    page:page.value
  },(res) => {
    console.log("返回值",res)
    CommentInfo.value  = res
    CommentInfo.value = changeTime(CommentInfo.value)
  })
}



import { NotifyPlugin } from 'tdesign-vue-next';

const replyData = ref('');

const submitReply = () => {
  NotifyPlugin.info({
    title: '回复内容',
    content: replyData.value,
    duration: 3000,
  });
};



SelectComment()


const comments = {
  username:"cci",
  replyUsername:"吻鲸看日落",
  content:"评论测试",
  time:"2023-06-26 16:38:00",
}

</script>

<template>

  <div>
    <t-layout>
      <t-header>
        <t-comment avatar="https://tdesign.gtimg.com/site/avatar.jpg">
          <template #content>
            <div class="form-container">
              <t-textarea v-model="replyData" placeholder="请输入内容" />
              <t-button class="form-submit" @click="submitReply">回复</t-button>
            </div>
          </template>
        </t-comment>
      </t-header>


      <t-content>
<!--           评论头-->
          <t-comment
              avatar="https://tdesign.gtimg.com/site/avatar.jpg"
              :author="comments.username"
              :datetime="comments.time"
              :content="comments.content"
              class="comment-reply"
          >
            <template #actions>
              <t-space key="thumbUp" :size="6">
                <t-icon name="thumb-up" />
                <span>6</span>
              </t-space>
              <div class="c-button" >回复</div>

            </template>

            <template #reply>
              <t-comment
                  avatar="https://tdesign.gtimg.com/site/avatar.jpg"
                  :datetime="11"
                  content="回复内容"
              >
                <template #author>
                  <span>{{comments.replyUsername}}</span>
                  <t-icon name="caret-right-small" size="medium" style="margin: 0 4px" />
                  <span>{{comments.username}}</span>
                </template>

                <template #actions>
                  <t-space key="thumbUp" :size="6">
                    <t-icon name="thumb-up" />
                    <span>6</span>
                  </t-space>
                  <div class="c-button" >回复</div>
                </template>
              </t-comment>
            </template>
          </t-comment>

      </t-content>
    </t-layout>
  </div>





</template>

<style scoped>
.form-container {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  .form-submit {
    margin-top: 8px;
  }
}
.c-button{
  width: 60px;
  height: 20px;
  color: #bdccf7;
  cursor: pointer;
  background-color: #e8eeff;
}
.c-button:hover{
  background-color: #e8eeff;
}


</style>