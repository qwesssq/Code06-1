package com.example.code06.SQL;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Picture {

    @SerializedName("code")
    private String code;
    @SerializedName("data")
    private data data1;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public data getData() {
        return data1;
    }

    public void setData(data data) {
        this.data1 = data;
    }



    public class data{

        @SerializedName("total")
        private String total;
        public String getTotal() {
            return total;
        }

        public void setTotal(String code) {
            this.total = code;
        }


//        private List<data.record> jsonArray;


        public record getRecord1() {
            return record1;
        }

        public void setRecord1(record record1) {
            this.record1 = record1;
        }
        @SerializedName("records")
        private record record1;


        public class record{
            @SerializedName("id")
            private int id;
//            @SerializedName("imageUrlList")
            private picuter image;
            public int getid() {
                return id;
            }

            public void setid(int id1) {
                this.id = id1;
            }

            public picuter getImage() {
                return image;
            }

            public void setImage(picuter id1) {
                this.image = id1;
            }
            public class picuter{
                private String imageid;
                public String getImageid(){
                    return imageid;
                }
                public void setImageid(String imageid){
                    this.imageid = imageid;
                }
            }

        }

//        public List<data.record> getJsonArray() {
//            return jsonArray;
//        }
//
//        public void setJsonArray(List<data.record> jsonArray) {
//            this.jsonArray = jsonArray;
//        }

    }
}
