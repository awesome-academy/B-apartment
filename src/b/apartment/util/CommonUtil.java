package b.apartment.util;

import java.util.List;

import org.springframework.util.DigestUtils;

public class CommonUtil {

	public static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}
	
	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static String gravatarURL(String email) {
		String gravatarId = DigestUtils.md5DigestAsHex(email.getBytes());
		return "https://secure.gravatar.com/avatar/" + gravatarId + "?s=80";
	}

}
