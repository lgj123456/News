package ad0424.yls.example.com.news.model;

import java.util.List;

/**
 * Created by yhdj on 2017/5/26.
 */

public class WeatherForcast {

    /**
     * resultcode : 200
     * reason : 查询成功!
     * result : [{"weatherid":"02","weather":"阴","temp1":"26","temp2":"30","sh":"05","eh":"08","date":"20170531","sfdate":"20170531050000","efdate":"20170531080000"},{"weatherid":"01","weather":"多云","temp1":"30","temp2":"31","sh":"08","eh":"11","date":"20170531","sfdate":"20170531080000","efdate":"20170531110000"},{"weatherid":"01","weather":"多云","temp1":"31","temp2":"30","sh":"11","eh":"14","date":"20170531","sfdate":"20170531110000","efdate":"20170531140000"},{"weatherid":"01","weather":"多云","temp1":"30","temp2":"26","sh":"14","eh":"17","date":"20170531","sfdate":"20170531140000","efdate":"20170531170000"},{"weatherid":"04","weather":"雷阵雨","temp1":"26","temp2":"25","sh":"17","eh":"20","date":"20170531","sfdate":"20170531170000","efdate":"20170531200000"},{"weatherid":"04","weather":"雷阵雨","temp1":"25","temp2":"25","sh":"20","eh":"23","date":"20170531","sfdate":"20170531200000","efdate":"20170531230000"},{"weatherid":"04","weather":"雷阵雨","temp1":"25","temp2":"25","sh":"23","eh":"02","date":"20170531","sfdate":"20170531230000","efdate":"20170601020000"},{"weatherid":"04","weather":"雷阵雨","temp1":"25","temp2":"26","sh":"02","eh":"05","date":"20170601","sfdate":"20170601020000","efdate":"20170601050000"},{"weatherid":"04","weather":"雷阵雨","temp1":"26","temp2":"30","sh":"05","eh":"08","date":"20170601","sfdate":"20170601050000","efdate":"20170601080000"},{"weatherid":"04","weather":"雷阵雨","temp1":"30","temp2":"31","sh":"08","eh":"11","date":"20170601","sfdate":"20170601080000","efdate":"20170601110000"},{"weatherid":"04","weather":"雷阵雨","temp1":"31","temp2":"30","sh":"11","eh":"14","date":"20170601","sfdate":"20170601110000","efdate":"20170601140000"},{"weatherid":"04","weather":"雷阵雨","temp1":"30","temp2":"26","sh":"14","eh":"17","date":"20170601","sfdate":"20170601140000","efdate":"20170601170000"},{"weatherid":"09","weather":"大雨","temp1":"26","temp2":"25","sh":"17","eh":"20","date":"20170601","sfdate":"20170601170000","efdate":"20170601200000"},{"weatherid":"09","weather":"大雨","temp1":"25","temp2":"24","sh":"20","eh":"23","date":"20170601","sfdate":"20170601200000","efdate":"20170601230000"},{"weatherid":"09","weather":"大雨","temp1":"24","temp2":"24","sh":"23","eh":"02","date":"20170601","sfdate":"20170601230000","efdate":"20170602020000"},{"weatherid":"09","weather":"大雨","temp1":"24","temp2":"26","sh":"02","eh":"05","date":"20170602","sfdate":"20170602020000","efdate":"20170602050000"},{"weatherid":"02","weather":"阴","temp1":"26","temp2":"28","sh":"05","eh":"08","date":"20170602","sfdate":"20170602050000","efdate":"20170602080000"},{"weatherid":"08","weather":"中雨","temp1":"28","temp2":"28","sh":"08","eh":"11","date":"20170602","sfdate":"20170602080000","efdate":"20170602110000"},{"weatherid":"09","weather":"大雨","temp1":"28","temp2":"27","sh":"11","eh":"14","date":"20170602","sfdate":"20170602110000","efdate":"20170602140000"},{"weatherid":"09","weather":"大雨","temp1":"27","temp2":"26","sh":"14","eh":"17","date":"20170602","sfdate":"20170602140000","efdate":"20170602170000"},{"weatherid":"09","weather":"大雨","temp1":"26","temp2":"25","sh":"17","eh":"20","date":"20170602","sfdate":"20170602170000","efdate":"20170602200000"},{"weatherid":"09","weather":"大雨","temp1":"25","temp2":"24","sh":"20","eh":"23","date":"20170602","sfdate":"20170602200000","efdate":"20170602230000"},{"weatherid":"09","weather":"大雨","temp1":"24","temp2":"24","sh":"23","eh":"02","date":"20170602","sfdate":"20170602230000","efdate":"20170603020000"},{"weatherid":"01","weather":"多云","temp1":"24","temp2":"25","sh":"02","eh":"05","date":"20170603","sfdate":"20170603020000","efdate":"20170603050000"},{"weatherid":"09","weather":"大雨","temp1":"25","temp2":"27","sh":"05","eh":"11","date":"20170603","sfdate":"20170603050000","efdate":"20170603110000"},{"weatherid":"09","weather":"大雨","temp1":"27","temp2":"24","sh":"11","eh":"17","date":"20170603","sfdate":"20170603110000","efdate":"20170603170000"},{"weatherid":"08","weather":"中雨","temp1":"24","temp2":"23","sh":"17","eh":"23","date":"20170603","sfdate":"20170603170000","efdate":"20170603230000"},{"weatherid":"04","weather":"雷阵雨","temp1":"23","temp2":"24","sh":"23","eh":"05","date":"20170603","sfdate":"20170603230000","efdate":"20170604050000"},{"weatherid":"04","weather":"雷阵雨","temp1":"24","temp2":"28","sh":"05","eh":"11","date":"20170604","sfdate":"20170604050000","efdate":"20170604110000"},{"weatherid":"04","weather":"雷阵雨","temp1":"28","temp2":"26","sh":"11","eh":"17","date":"20170604","sfdate":"20170604110000","efdate":"20170604170000"},{"weatherid":"04","weather":"雷阵雨","temp1":"26","temp2":"23","sh":"17","eh":"23","date":"20170604","sfdate":"20170604170000","efdate":"20170604230000"},{"weatherid":"04","weather":"雷阵雨","temp1":"23","temp2":"25","sh":"23","eh":"05","date":"20170604","sfdate":"20170604230000","efdate":"20170605050000"},{"weatherid":"04","weather":"雷阵雨","temp1":"25","temp2":"28","sh":"05","eh":"11","date":"20170605","sfdate":"20170605050000","efdate":"20170605110000"},{"weatherid":"04","weather":"雷阵雨","temp1":"28","temp2":"26","sh":"11","eh":"17","date":"20170605","sfdate":"20170605110000","efdate":"20170605170000"},{"weatherid":"04","weather":"雷阵雨","temp1":"26","temp2":"23","sh":"17","eh":"23","date":"20170605","sfdate":"20170605170000","efdate":"20170605230000"},{"weatherid":"04","weather":"雷阵雨","temp1":"23","temp2":"25","sh":"23","eh":"05","date":"20170605","sfdate":"20170605230000","efdate":"20170606050000"},{"weatherid":"04","weather":"雷阵雨","temp1":"25","temp2":"27","sh":"05","eh":"11","date":"20170606","sfdate":"20170606050000","efdate":"20170606110000"},{"weatherid":"04","weather":"雷阵雨","temp1":"27","temp2":"26","sh":"11","eh":"17","date":"20170606","sfdate":"20170606110000","efdate":"20170606170000"},{"weatherid":"08","weather":"中雨","temp1":"26","temp2":"25","sh":"17","eh":"23","date":"20170606","sfdate":"20170606170000","efdate":"20170606230000"},{"weatherid":"08","weather":"中雨","temp1":"25","temp2":"25","sh":"23","eh":"05","date":"20170606","sfdate":"20170606230000","efdate":"20170607050000"}]
     * error_code : 0
     */

    private String resultcode;
    private String reason;
    private int error_code;
    /**
     * weatherid : 02
     * weather : 阴
     * temp1 : 26
     * temp2 : 30
     * sh : 05
     * eh : 08
     * date : 20170531
     * sfdate : 20170531050000
     * efdate : 20170531080000
     */

    private List<ResultBean> result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        private String weatherid;
        private String weather;
        private String temp1;
        private String temp2;
        private String sh;
        private String eh;
        private String date;
        private String sfdate;
        private String efdate;

        public String getWeatherid() {
            return weatherid;
        }

        public void setWeatherid(String weatherid) {
            this.weatherid = weatherid;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public String getTemp1() {
            return temp1;
        }

        public void setTemp1(String temp1) {
            this.temp1 = temp1;
        }

        public String getTemp2() {
            return temp2;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }

        public String getSh() {
            return sh;
        }

        public void setSh(String sh) {
            this.sh = sh;
        }

        public String getEh() {
            return eh;
        }

        public void setEh(String eh) {
            this.eh = eh;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getSfdate() {
            return sfdate;
        }

        public void setSfdate(String sfdate) {
            this.sfdate = sfdate;
        }

        public String getEfdate() {
            return efdate;
        }

        public void setEfdate(String efdate) {
            this.efdate = efdate;
        }
    }
}
