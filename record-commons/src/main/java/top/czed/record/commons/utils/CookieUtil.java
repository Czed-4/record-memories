package top.czed.record.commons.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @Author Czed
 * @Date 2021-1-12
 * @Description
 * @Version 1.0
 */
public class CookieUtil {

    /**
     * 获取Cookie的值(不解码)
     *
     * @param request   请求
     * @param cookieKey Cookie键
     * @return Cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey) {
        return getCookieValue(request, cookieKey, false);
    }

    /**
     * 获取Cookie的值
     *
     * @param request   请求
     * @param cookieKey Cookie键
     * @param isDecode  是否解码
     * @return Cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey, boolean isDecode) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieKey == null) {
            return null;
        }
        String cookieValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieKey)) {
                    cookieValue = isDecode ? URLDecoder.decode(cookie.getValue(), "UTF-8") : cookie.getValue();
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookieValue;
    }

    /**
     * 获取Cookie的值
     *
     * @param request    请求
     * @param cookieKey  Cookie键
     * @param encodeType 编码格式
     * @return Cookie值
     */
    public static String getCookieValue(HttpServletRequest request, String cookieKey, String encodeType) {
        Cookie[] cookieList = request.getCookies();
        if (cookieList == null || cookieKey == null) {
            return null;
        }
        String cookieValue = null;
        try {
            for (Cookie cookie : cookieList) {
                if (cookie.getName().equals(cookieKey)) {
                    cookieValue = URLDecoder.decode(cookie.getValue(), encodeType);
                    break;
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return cookieValue;
    }

    /**
     * 设置Cookie的值,默认浏览器关闭即失效,也不编码
     *
     * @param request     请求
     * @param response    响应
     * @param cookieKey   Cookie键
     * @param cookieValue Cookie值
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey, String cookieValue) {
        setCookie(request, response, cookieKey, cookieValue, -1);
    }

    /**
     * 设置Cookie的值,在指定时间内生效,但不编码
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie键
     * @param cookieValue  Cookie值
     * @param cookieMaxAge Cookie生效的最大秒数
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge) {
        setCookie(request, response, cookieName, cookieValue, cookieMaxAge, false);
    }

    /**
     * 设置Cookie的值,不设置生效时间
     *
     * @param request     请求
     * @param response    响应
     * @param cookieName  Cookie键
     * @param cookieValue Cookie值
     * @param isEncode    是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, boolean isEncode) {
        setCookie(request, response, cookieName, cookieValue, -1, isEncode);
    }

    /**
     * 设置Cookie的值,在指定时间内生效,编码
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie键
     * @param cookieValue  Cookie值
     * @param cookieMaxAge Cookie生效的最大秒数
     * @param isEncode     是否编码
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, boolean isEncode) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, isEncode);
    }

    /**
     * 设置Cookie的值,在指定时间内生效,指定编码类型
     *
     * @param request      请求
     * @param response     响应
     * @param cookieName   Cookie键
     * @param cookieValue  Cookie值
     * @param cookieMaxAge Cookie生效的最大秒数
     * @param encodeType   编码类型
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String cookieValue, int cookieMaxAge, String encodeType) {
        doSetCookie(request, response, cookieName, cookieValue, cookieMaxAge, encodeType);
    }

    /**
     * 删除Cookie
     *
     * @param request   请求
     * @param response  响应
     * @param cookieKey Cookie键
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey) {
        doSetCookie(request, response, cookieKey, "", -1, false);
    }

    /**
     * 设置Cookie的值,并使其在指定时间内生效
     *
     * @param request      请求
     * @param response     响应
     * @param cookieKey    Cookie键
     * @param cookieValue  Cookie值
     * @param cookieMaxAge Cookie生效的最大秒数
     * @param isEncode     是否编码
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey, String cookieValue, int cookieMaxAge, boolean isEncode) {
        try {
            if (StringUtils.isBlank(cookieValue)) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            set(request, response, cookieKey, cookieValue, cookieMaxAge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void set(HttpServletRequest request, HttpServletResponse response, String cookieKey, String cookieValue, int cookieMaxAge) {
        Cookie cookie = new Cookie(cookieKey, cookieValue);
        if (cookieMaxAge > 0) {
            cookie.setMaxAge(cookieMaxAge);
        }
        if (request != null) {
            // 设置域名的Cookie
            String localhost = "localhost";
            String domainName = getDomainName(request);
            if (!localhost.equals(domainName)) {
                cookie.setDomain(domainName);
            }
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 设置Cookie的值,并使其在指定时间内生效
     *
     * @param request      请求
     * @param response     响应
     * @param cookieKey    Cookie名称
     * @param cookieValue  Cookie值
     * @param cookieMaxAge cookie生效的最大秒数
     * @param encodeType   编码格式
     */
    private static void doSetCookie(HttpServletRequest request, HttpServletResponse response, String cookieKey, String cookieValue, int cookieMaxAge, String encodeType) {
        try {
            if (StringUtils.isBlank(cookieValue)) {
                cookieValue = "";
            } else {
                cookieValue = URLEncoder.encode(cookieValue, encodeType);
            }
            set(request, response, cookieKey, cookieValue, cookieMaxAge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Cookie的域名
     *
     * @param request 请求
     * @return 域名
     */
    private static String getDomainName(HttpServletRequest request) {
        String domainName;
        String serverName = request.getRequestURL().toString();
        if (StringUtils.isBlank(serverName)) {
            domainName = "";
        } else {
            serverName = serverName.toLowerCase();
            serverName = serverName.substring(7);
            final int end = serverName.indexOf("/");
            serverName = serverName.substring(0, end);
            final String[] domains = serverName.split("\\.");
            int length = domains.length;
            int www = 3;
            if (length > www) {
                // www.xxx.xxx
                domainName = "." + domains[length - 3] + "." + domains[length - 2] + "." + domains[length - 1];
            } else if (length > 1) {
                // xxx.xxx
                domainName = "." + domains[length - 2] + "." + domains[length - 1];
            } else {
                domainName = serverName;
            }
        }
        String mark = ":";
        if (StringUtils.isNotBlank(domainName) && domainName.indexOf(mark) > 0) {
            String[] ary = domainName.split(mark);
            domainName = ary[0];
        }
        return domainName;
    }

}