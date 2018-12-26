import axios from 'axios';

let base = 'http://localhost:8081';

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data); };

export const getUserList = params => { return axios.get(`${base}/sign/list/${params.page}/${params.pageSize}`, { params: params }); };

export const getUserListPage = params => { return axios.get(`${base}/sign/list/${params.page}/${params.pageSize}`, { params: params }); };

export const removeContact = params => { return axios.post(`${base}/sign/remove`, { ids : params }); };

export const editContact = params => { return axios.get(`${base}/sign/edit`, { params: params }); };

export const addContact = params => { return axios.post(`${base}/sign/save`, params); };