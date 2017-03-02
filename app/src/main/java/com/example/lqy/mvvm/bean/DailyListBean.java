package com.example.lqy.mvvm.bean;

import java.util.List;

/**
 * Created by qiyao on 2017/2/15.
 */

public class DailyListBean {

    /**
     * date : 20170215
     * stories : [{"images":["http://pic1.zhimg.com/f8c28115d7afce5aacbd7deebba3b540.jpg"],"type":0,"id":9224154,"ga_prefix":"021513","title":"看清楚，写了这些字的湿疹膏不要买"},{"images":["http://pic1.zhimg.com/cf3c6cd45b2ff87891305d5a9e0f88e4.jpg"],"type":0,"id":9223574,"ga_prefix":"021512","title":"大误 · 我是世界的中心"},{"images":["http://pic3.zhimg.com/ccd4e6783a4a64e74230bc1c4728c42e.jpg"],"type":0,"id":9222546,"ga_prefix":"021511","title":"大学会终身雇聘任教授，企业为什么不终身雇佣好员工呢？"},{"images":["http://pic4.zhimg.com/ccd9e65d4723b79475649eabbd966a5b.jpg"],"type":0,"id":9198798,"ga_prefix":"021510","title":"只有「擅长」是不够的，对职场新人来说探索自我更重要"},{"images":["http://pic2.zhimg.com/295d50cf0353b1db26614b64826ce5c9.jpg"],"type":0,"id":9222998,"ga_prefix":"021509","title":"情人节过去了，来用科学技术预测下你们的爱情能不能长久"},{"images":["http://pic4.zhimg.com/b13302ecc63b3c99356f99f4ed01c237.jpg"],"type":0,"id":9221188,"ga_prefix":"021508","title":"加入电竞行业并不一定要当选手，来听听职业俱乐部怎么说"},{"title":"其实，柚子茶和柚子没半毛钱关系","ga_prefix":"021507","images":["http://pic3.zhimg.com/228d836547ccba5d0fbeae6755704bd6.jpg"],"multipic":true,"type":0,"id":9223117},{"images":["http://pic3.zhimg.com/422d1535e3ecd6c4bed36ab366f8556e.jpg"],"type":0,"id":9222989,"ga_prefix":"021507","title":"看完《爱乐之城》，回想起自己当初为什么会爱上电影"},{"images":["http://pic2.zhimg.com/74eddd8c32dfaf063c06c34da6b7ce21.jpg"],"type":0,"id":9222960,"ga_prefix":"021507","title":"去世后仍拿到五座格莱美，大卫 · 鲍伊如何影响了摇滚乐？"},{"images":["http://pic4.zhimg.com/7bc5b680d1ab4f9dde3504409f756593.jpg"],"type":0,"id":9218744,"ga_prefix":"021506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic3.zhimg.com/4773aef383aea09ff690b7185acc31ba.jpg","type":0,"id":9222998,"ga_prefix":"021509","title":"情人节过去了，来用科学技术预测下你们的爱情能不能长久"},{"image":"http://pic2.zhimg.com/e57714bf0b92588bd961bb46e952b1d5.jpg","type":0,"id":9222989,"ga_prefix":"021507","title":"看完《爱乐之城》，回想起自己当初为什么会爱上电影"},{"image":"http://pic2.zhimg.com/ab1544b3f86fe75a9f43cdb829dc2f11.jpg","type":0,"id":9222960,"ga_prefix":"021507","title":"去世后仍拿到五座格莱美，大卫 · 鲍伊如何影响了摇滚乐？"},{"image":"http://pic3.zhimg.com/e5e25523ac8f269d76286c5768ddf23a.jpg","type":0,"id":9222322,"ga_prefix":"021420","title":"如何科学避孕？"},{"image":"http://pic3.zhimg.com/11e27183cdb0dad2ce2551f0d9c2300a.jpg","type":0,"id":9221810,"ga_prefix":"021416","title":"小偷偷东西不按套路出牌，法官急得满身大汗"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * images : ["http://pic1.zhimg.com/f8c28115d7afce5aacbd7deebba3b540.jpg"]
         * type : 0
         * id : 9224154
         * ga_prefix : 021513
         * title : 看清楚，写了这些字的湿疹膏不要买
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;
        public static final String TYPE_NEWS = "news";

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : http://pic3.zhimg.com/4773aef383aea09ff690b7185acc31ba.jpg
         * type : 0
         * id : 9222998
         * ga_prefix : 021509
         * title : 情人节过去了，来用科学技术预测下你们的爱情能不能长久
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
