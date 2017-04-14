package mzy.bc.com.buyizhibo.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/4/12.
 */

public class ReMenBean {



    private ResultBean result;
    private int error_code;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public static class ResultBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * created_at : 1489911617321
             * updated_at : 1489911620649
             * id : 1172224368246786
             * data : {"status":1,"live_type":1,"pic":"http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg","live_name":"andriod "}
             * uid : 1172056579309569
             * user : {"user_data":{"user_name":"SKY\nSKY\nSKY","avatar":"http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg","sign":"僵僵：\u201c我是单身僵啊，好巧吧，那我们一起吖\u201d。  XX：\u201c那你也吃草啊？\u201d "},"id":1172056579309569,"created_at":1489901616546,"updated_at":1489901616587}
             */

            private long created_at;
            private long updated_at;
            private long id;
            private DataBean data;
            private long uid;
            private UserBean user;

            public long getCreated_at() {
                return created_at;
            }

            public void setCreated_at(long created_at) {
                this.created_at = created_at;
            }

            public long getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(long updated_at) {
                this.updated_at = updated_at;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public long getUid() {
                return uid;
            }

            public void setUid(long uid) {
                this.uid = uid;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class DataBean {
                /**
                 * status : 1
                 * live_type : 1
                 * pic : http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg
                 * live_name : andriod
                 */

                private int status;
                private int live_type;
                private String pic;
                private String live_name;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getLive_type() {
                    return live_type;
                }

                public void setLive_type(int live_type) {
                    this.live_type = live_type;
                }

                public String getPic() {
                    return pic;
                }

                public void setPic(String pic) {
                    this.pic = pic;
                }

                public String getLive_name() {
                    return live_name;
                }

                public void setLive_name(String live_name) {
                    this.live_name = live_name;
                }
            }

            public static class UserBean {
                /**
                 * user_data : {"user_name":"SKY\nSKY\nSKY","avatar":"http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg","sign":"僵僵：\u201c我是单身僵啊，好巧吧，那我们一起吖\u201d。  XX：\u201c那你也吃草啊？\u201d "}
                 * id : 1172056579309569
                 * created_at : 1489901616546
                 * updated_at : 1489901616587
                 */

                private UserDataBean user_data;
                private long id;
                private long created_at;
                private long updated_at;

                public UserDataBean getUser_data() {
                    return user_data;
                }

                public void setUser_data(UserDataBean user_data) {
                    this.user_data = user_data;
                }

                public long getId() {
                    return id;
                }

                public void setId(long id) {
                    this.id = id;
                }

                public long getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(long created_at) {
                    this.created_at = created_at;
                }

                public long getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(long updated_at) {
                    this.updated_at = updated_at;
                }

                public static class UserDataBean {
                    /**
                     * user_name : SKY
                     SKY
                     SKY
                     * avatar : http://img4.imgtn.bdimg.com/it/u=3547796456,889162116&fm=23&gp=0.jpg
                     * sign : 僵僵：“我是单身僵啊，好巧吧，那我们一起吖”。  XX：“那你也吃草啊？”
                     */

                    private String user_name;
                    private String avatar;
                    private String sign;

                    public String getUser_name() {
                        return user_name;
                    }

                    public void setUser_name(String user_name) {
                        this.user_name = user_name;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getSign() {
                        return sign;
                    }

                    public void setSign(String sign) {
                        this.sign = sign;
                    }
                }
            }
        }
    }
}
