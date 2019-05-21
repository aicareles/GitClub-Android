package com.i502tech.gitclub.code.bean;

/**
 * description $desc$
 * created by jerry on 2019/4/12.
 */
public class Article {

    /**
     * articleId : 2227
     * category : Android
     * childCategory : 0
     * comments : 0
     * contributor :
     * contributorId : 2
     * date : 2019-04-12 00:00:00
     * des : 一个从Google Play, GitHub, Amazon 或者 F-Droid上检查app更新的library。
     * imgUrl : https://gitclub.502tech.com/GitClub/image/12140D4ACA1E4338BCB1ECA1DF126793.gif?Expires=1852706744&OSSAccessKeyId=LTAI4KXTQGO5UMVQ&Signature=SL3Hs987cihx3ziK95yZ7am0Qxo%3D
     * link : https://github.com/javiersantos/AppUpdater
     * rank : 0
     * reviewStatus : 1
     * stars : 0
     * tag : 网络
     * title : AppUpdater
     * unStars : 0
     * updateDate : 2019-04-12 09:24:33
     * user : {"adminStatus":1,"avatar":"https://wx.qlogo.cn/mmopen/vi_32/ZtYmxPFhJgn29xuLFiatw3xI55ekSGZBTwoxzj5KEtR8qvz2bclwrv3EUicFxva9ODMCBStHZ3ehmFtyPQU4Edaw/132","city":"","date":"2018-09-09 14:40:13","gender":"","userName":"V V","openId":"oIXfM4r2dHJSSjr_udB6v_hwMoWI","score":0,"sessionKey":"+d5wnhlJrl/VAETsaQtVyw==","userId":2}
     * views : 2
     * wrapLink : i6Fzya
     */

    private int articleId;
    private String category;
    private int childCategory;
    private int comments;
    private String contributor;
    private int contributorId;
    private String date;
    private String des;
    private String imgUrl;
    private String link;
    private int rank;
    private int reviewStatus;
    private int stars;
    private String tag;
    private String title;
    private int unStars;
    private String updateDate;
    private UserBean user;
    private int views;
    private String wrapLink;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getChildCategory() {
        return childCategory;
    }

    public void setChildCategory(int childCategory) {
        this.childCategory = childCategory;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public int getContributorId() {
        return contributorId;
    }

    public void setContributorId(int contributorId) {
        this.contributorId = contributorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(int reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUnStars() {
        return unStars;
    }

    public void setUnStars(int unStars) {
        this.unStars = unStars;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getWrapLink() {
        return wrapLink;
    }

    public void setWrapLink(String wrapLink) {
        this.wrapLink = wrapLink;
    }

    public static class UserBean {
        /**
         * adminStatus : 1
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/ZtYmxPFhJgn29xuLFiatw3xI55ekSGZBTwoxzj5KEtR8qvz2bclwrv3EUicFxva9ODMCBStHZ3ehmFtyPQU4Edaw/132
         * city :
         * date : 2018-09-09 14:40:13
         * gender :
         * userName : V V
         * openId : oIXfM4r2dHJSSjr_udB6v_hwMoWI
         * score : 0
         * sessionKey : +d5wnhlJrl/VAETsaQtVyw==
         * userId : 2
         */

        private int adminStatus;
        private String avatar;
        private String city;
        private String date;
        private String gender;
        private String userName;
        private String openId;
        private int score;
        private String sessionKey;
        private int userId;

        public int getAdminStatus() {
            return adminStatus;
        }

        public void setAdminStatus(int adminStatus) {
            this.adminStatus = adminStatus;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getSessionKey() {
            return sessionKey;
        }

        public void setSessionKey(String sessionKey) {
            this.sessionKey = sessionKey;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
