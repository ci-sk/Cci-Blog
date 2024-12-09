<script setup>
import { ref, onMounted, onUnmounted, computed } from 'vue'
import { Bell } from '@element-plus/icons-vue'
import { get, post } from '../../net/index'

const drawerVisible = ref(false)
const activeTab = ref('unread')
const unreadCount = ref(0)
const messages = ref([])
const comments = ref([])

// 计算未读消息列表
const unreadMessages = computed(() => {
  return messages.value.filter(msg => !msg.isRead)
})

// 计算未读评论列表
const unreadComments = computed(() => {
  return comments.value.filter(comment => !comment.isRead)
})

// 计算总未读数
const totalUnreadCount = computed(() => {
  return unreadMessages.value.length + unreadComments.value.length
})

// 获取未读消息数量
const getUnreadCount = () => {
  get('/api/message/unread/count', (message) => {
    unreadCount.value = message
  })

  // 获取未读评论数量
  get('/api/comments/unread/count', (count) => {
    console.log("未读评论", count)
  })
}

// 获取最近消息
const getMessages = () => {
  get('/api/message/recent', (message) => {
    messages.value = message
  })
}

// 获取最近评论
const getComments = () => {
  get('/api/comments/recent', (data) => {
    comments.value = data
    console.log("最近评论", data)
  })
}

// 标记所有为已读
const markAllAsRead = () => {
  post('/api/message/read/all', {}, () => {
    unreadCount.value = 0
  })
  // 标记评论已读
  post('/api/comments/read/all', {}, () => {
    comments.value = comments.value.map(comment => ({...comment, isRead: 1}))
  })
}

// 显示通知抽屉
const showNotifications = () => {
  drawerVisible.value = true
  getMessages()
  getComments()
  if (totalUnreadCount.value > 0) {
    markAllAsRead()
  }
}

onMounted(() => {
  getUnreadCount()
  const timer = setInterval(getUnreadCount, 60000) // 每分钟检查一次

  onUnmounted(() => {
    clearInterval(timer)
  })
})
</script>


<template>
  <div class="notification-bell">
    <el-badge :value="totalUnreadCount" :hidden="totalUnreadCount === 0">
      <el-button :icon="Bell" @click="showNotifications" />
    </el-badge>

    <el-drawer
      v-model="drawerVisible"
      title="消息通知"
      direction="rtl"
      size="400px"
    >
      <el-tabs v-model="activeTab">
        <el-tab-pane label="未读消息" name="unread">
          <el-tabs>
            <el-tab-pane label="留言" name="messages">
              <el-timeline>
                <el-timeline-item
                  v-for="msg in unreadMessages"
                  :key="msg.mid"
                  :timestamp="msg.time"
                  type="primary"
                >
                  <h4>{{ msg.username }}</h4>
                  <p>{{ msg.content }}</p>
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
            <el-tab-pane label="评论" name="comments">
              <el-timeline>
                <el-timeline-item
                  v-for="comment in unreadComments"
                  :key="comment.cid"
                  :timestamp="comment.time"
                  type="primary"
                >
                  <h4>{{ comment.username }}</h4>
                  <p>{{ comment.content }}</p>
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
        <el-tab-pane label="全部消息" name="all">
          <el-tabs>
            <el-tab-pane label="留言" name="all-messages">
              <el-timeline>
                <el-timeline-item
                  v-for="msg in messages"
                  :key="msg.mid"
                  :timestamp="msg.time"
                  :type="msg.isRead ? 'info' : 'primary'"
                >
                  <h4>{{ msg.username }}</h4>
                  <p>{{ msg.content }}</p>
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
            <el-tab-pane label="评论" name="all-comments">
              <el-timeline>
                <el-timeline-item
                  v-for="comment in comments"
                  :key="comment.cid"
                  :timestamp="comment.time"
                  :type="comment.isRead ? 'info' : 'primary'"
                >
                  <h4>{{ comment.username }}</h4>
                  <p>{{ comment.content }}</p>
                </el-timeline-item>
              </el-timeline>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
      </el-tabs>
    </el-drawer>
  </div>
</template>



<style scoped>
.notification-bell {
  margin-right: 20px;
}

.el-timeline-item {
  padding-bottom: 20px;
}

.el-timeline-item h4 {
  margin: 0;
  font-size: 14px;
  color: #409EFF;
}

.el-timeline-item p {
  margin: 5px 0 0;
  font-size: 13px;
  color: #606266;
}
</style>