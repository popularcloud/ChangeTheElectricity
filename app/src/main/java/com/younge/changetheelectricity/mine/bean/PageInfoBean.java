package com.younge.changetheelectricity.mine.bean;

public class PageInfoBean {
    /**
     * pageInfo : {"id":2,"category_id":0,"type":"page","title":"隐私政策法律法规","keywords":"","description":"","flag":"","image":"","content":"","icon":"","views":0,"likes":0,"dislikes":0,"comments":0,"diyname":"policy","createtime":1593602433,"updatetime":1593602433,"weigh":2}
     */

    private PageInfo pageInfo;

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfo {
        /**
         * id : 2
         * category_id : 0
         * type : page
         * title : 隐私政策法律法规
         * keywords :
         * description :
         * flag :
         * image :
         * content :
         * icon :
         * views : 0
         * likes : 0
         * dislikes : 0
         * comments : 0
         * diyname : policy
         * createtime : 1593602433
         * updatetime : 1593602433
         * weigh : 2
         */

        private int id;
        private int category_id;
        private String type;
        private String title;
        private String keywords;
        private String description;
        private String flag;
        private String image;
        private String content;
        private String icon;
        private int views;
        private int likes;
        private int dislikes;
        private int comments;
        private String diyname;
        private int createtime;
        private int updatetime;
        private int weigh;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public int getDislikes() {
            return dislikes;
        }

        public void setDislikes(int dislikes) {
            this.dislikes = dislikes;
        }

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getDiyname() {
            return diyname;
        }

        public void setDiyname(String diyname) {
            this.diyname = diyname;
        }

        public int getCreatetime() {
            return createtime;
        }

        public void setCreatetime(int createtime) {
            this.createtime = createtime;
        }

        public int getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(int updatetime) {
            this.updatetime = updatetime;
        }

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }
    }
}
