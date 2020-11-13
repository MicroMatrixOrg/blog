var storge = {
  set (key, value) {
    sessionStorage.setItem(key, JSON.stringify(value));
  },
  get (key) {
    if (sessionStorage.getItem(key) == undefined) return [];
    return JSON.parse(sessionStorage.getItem(key));
  },
  remove (key) {
    sessionStorage.removeItem(key);
  }
}
export default storge;