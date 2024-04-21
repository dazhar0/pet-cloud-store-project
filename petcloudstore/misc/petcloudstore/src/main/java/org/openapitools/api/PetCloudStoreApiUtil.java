package org.openapitools.api;


import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PetCloudStoreApiUtil {
    public static void setCustomResponse(NativeWebRequest request, String contentType, String customContent) {
        try {
            HttpServletResponse response = request.getNativeResponse(HttpServletResponse.class);
            response.setCharacterEncoding("UTF-8");
            response.addHeader("Content-Type", contentType);
            response.getWriter().print(customContent);
        } catch (IOException ex) {
            throw new CustomRuntimeException(ex);
        }
    }
}
