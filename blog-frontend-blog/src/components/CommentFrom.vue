<!--
通过QQ号查询头像
http://q.qlogo.cn/headimg_dl?dst_uin=QQ号码&spec=640&img_type=jpg
QQ昵称
http://users.qzone.qq.com/fcg-bin/cgi_get_portrait.fcg?uins=QQ号码
-->

<script setup>
import {ref, watch, onMounted} from "vue";
import {createAccount, getIsQQ, submComment} from "../utils/store.js";
import {IsQQ} from "../utils/index.js";

const form = ref({
  id: "",
  name: "",
  email: "",
  website: "",
  avatar: "",
  content: "",
});

const props = defineProps({
  aid: {
    type: Number,
    required: true,
    default:0
  }
});

// 字数统计
const contentLength = ref(0);
const replyContentLength = ref(0);
const maxLength = 500;

//获取QQ昵称
const getNameByQQ = async (qq) => {
  try {
    const response = await axios.get(`/api/qq-info?qq=${qq}`);
    if (response.data.name) {
      form.value.name = response.data.name;
      return true;
    }
    return false;
  } catch (error) {
    console.error('获取QQ昵称失败:', error);
    return false;
  }
};

// 自动填充QQ邮箱和昵称
watch(() => form.value.name, async (newVal) => {
  // 如果是QQ号，则自动填充邮箱和昵称
  if (IsQQ(newVal)) {
    form.value.email = `${newVal}@qq.com`;
    form.value.avatar = `http://q.qlogo.cn/headimg_dl?dst_uin=${newVal}&spec=640&img_type=jpg`;
    await getNameByQQ(newVal);
    // 保存表单数据到localStorage
  } else {
    form.value.avatar = `https://api.dicebear.com/7.x/avataaars/svg?seed=${form.value.name}`;
  }
});

// 查询用户是否存在
const getQQInfo = async () => {
  let data = form.value.name
  if(form.value.name === "") data = form.value.email;
  let res = await getIsQQ(data)
  console.log(res);
  // 如果用户存在，则填充表单
  if (res) {
    form.value.id = res.uid;
    form.value.name = res.username;
    form.value.avatar = res.avatar;
    form.value.email = res.email;
    form.value.website = res.website;
    console.log(res);
    return true;
  }else{
    return false;
  }
}

// 提交评论
const submitComment = async () => {
  if (!form.value.name || !form.value.email || !form.value.content) return;
  if(! await getQQInfo()){
    //如果查不到用户就创建按新用户
    let res  = await createAccount({
      id: IsQQ(form.value.name) ? form.value.name :'',
      username: form.value.name || form.value.id,
      email: form.value.email,
      website: form.value.website,
      avatar: form.value.avatar,
    })
    console.log(res);
    if(res){
      form.value.id = res.uid;
    }
  }
  // 提交评论
  let data = await submComment({
    aid: props.aid,
    uid: form.value.id,
    content: form.value.content,
  })
  console.log(data);
  form.value.content = "";
  localStorage.setItem('commentForm', JSON.stringify(form.value));
  form.value.content = "";
  contentLength.value = 0;
};

// 监听内容变化更新字数
const updateContentLength = (event) => {
  contentLength.value = event.target.value.length;
};

// 监听回复内容变化更新字数
const updateReplyContentLength = (event) => {
  replyContentLength.value = event.target.value.length;
};

onMounted(()=>{
  const savedForm = localStorage.getItem('commentForm');
  if (savedForm) {
    form.value = JSON.parse(savedForm);
  }
})
</script>

<template>
  <!-- 评论表单 -->
  <div class="card bg-base-200 shadow-xl mb-8">
    <div class="card-body">
      <!-- 头像预览 -->
      <div class="flex flex-col lg:flex-row gap-4">
        <div class="">
          <div class="avatar">
            <div class="w-16 rounded-full ring ring-offset-base-100 ">
              <img
                  :src="form.avatar"
                  alt="Avatar Preview"
              />
            </div>
          </div>
        </div>
        <div class="flex-1">
          <form @submit.prevent="submitComment">
            <div class="grid grid-cols-1 lg:grid-cols-3 gap-4 mb-4">
              <div>
                <label class="label">
                  <span class="label-text">昵称</span>
                  <span class="label-text-alt text-error">必填</span>
                </label>
                <input
                    v-model="form.name"
                    type="text"
                    class="input input-bordered w-full"
                    placeholder="请输入你的昵称"
                    required
                />
              </div>
              <div>
                <label class="label">
                  <span class="label-text">邮箱</span>
                  <span class="label-text-alt text-error">必填</span>
                </label>
                <input
                    v-model="form.email"
                    type="email"
                    class="input input-bordered w-full"
                    placeholder="请输入你的邮箱"
                    required
                />
              </div>
              <div>
                <label class="label">
                  <span class="label-text">网址</span>
                  <span class="label-text-alt">选填</span>
                </label>
                <input
                    v-model="form.website"
                    type="url"
                    class="input input-bordered w-full"
                    placeholder="请输入你的网站"
                />
              </div>
            </div>
            <div class="mb-4">
              <label class="label">
                <span class="label-text">评论内容</span>
                <span class="label-text-alt text-error">必填</span>
              </label>
              <textarea
                  v-model="form.content"
                  @input="updateContentLength"
                  class="textarea textarea-bordered w-full h-32"
                  placeholder="🐧在昵称框输入QQ号会自动获取邮箱和昵称，其中必填项为昵称和邮箱"
                  required
                  maxlength="500"
              ></textarea>
              <div class="flex justify-end mt-1">
            <span class="text-sm text-base-content/60">
              {{ contentLength }}/{{ maxLength }}
            </span>
              </div>
            </div>
            <div class="flex justify-end">
              <button
                  type="submit"
                  class="btn btn-primary"
                  :disabled="!form.name || !form.email || !form.content"
              >
                发送
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
