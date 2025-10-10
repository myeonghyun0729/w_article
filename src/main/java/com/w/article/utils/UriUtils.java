package com.w.article.utils;

public class UriUtils {

    public static String getListUrl(String cafeId, String menuId) {
        StringBuilder sb = new StringBuilder();
        // String url = "https://apis.naver.com/cafe-web/cafe-boardlist-api/v1/cafes/10879137/menus/404/articles";
        sb.append("https://apis.naver.com/cafe-web/cafe-boardlist-api/v1/cafes/")
                .append(cafeId)
                .append("/menus/")
                .append(menuId)
                .append("/articles");
        return sb.toString();
    }

    public static String getDetailUrl(String cafeId, String menuId, String articleId) {
        // https://cafe.naver.com/f-e/cafes/10879137/articles/660434?boardtype=L&menuid=404&referrerAllArticles=false
        StringBuilder sb = new StringBuilder();
        sb.append("https://cafe.naver.com/f-e/cafes/")
                .append(cafeId)
                .append("/articles/")
                .append(articleId)
                .append("?menuid=")
                .append(menuId)
                .append("&referrerAllArticles=false");
        return sb.toString();
    }
}
