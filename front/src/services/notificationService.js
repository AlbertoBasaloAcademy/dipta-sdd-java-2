import { reactive } from 'vue';

export const notificationState = reactive({
  message: '',
  type: '', // 'success' or 'error'
  visible: false
});

let timeout = null;

export const notify = (message, type = 'success') => {
  if (timeout) clearTimeout(timeout);
  
  notificationState.message = message;
  notificationState.type = type;
  notificationState.visible = true;

  timeout = setTimeout(() => {
    notificationState.visible = false;
  }, 5000);
};
