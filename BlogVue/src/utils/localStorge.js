var storge = {
    set(key, value) {
        localStorage.setItem(key, JSON.stringify(value));
    },
    get(key) {
        if (localStorage.getItem(key) == undefined) return [];
        return JSON.parse(localStorage.getItem(key));
    },
    remove(key) {
        localStorage.removeItem(key);
    }
}
export default storge;