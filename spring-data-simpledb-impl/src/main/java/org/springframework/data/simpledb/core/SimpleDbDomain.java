package org.springframework.data.simpledb.core;

import static org.springframework.data.simpledb.annotation.CustomDomain.CUSTOM_DOMAIN_NAME_METHOD;
import static org.springframework.util.ReflectionUtils.invokeMethod;

import java.lang.reflect.Method;

import org.springframework.data.simpledb.annotation.CustomDomain;
import org.springframework.data.simpledb.annotation.DomainPrefix;
import org.springframework.data.simpledb.util.StringUtil;

public class SimpleDbDomain {

	private String domainPrefix;

	public SimpleDbDomain() {
		
	}
	
	public SimpleDbDomain(final String domainPrefix) {
		this.domainPrefix = domainPrefix;
	}
	
	/**
	 * Domain name are computed based on class names: UserJob -> user_job, or 
	 * the class's getDomainName() method is invoked if the {@link CustomDomain} 
	 * annotation is present
	 * 
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("all")
	public String getDomain(Class<?> clazz) {
		StringBuilder ret = new StringBuilder();

		String computedDomainPrefix = getDomainPrefix(clazz);
		if(computedDomainPrefix != null) {
			ret.append(computedDomainPrefix);
			ret.append(".");
		}

		String domain = null;
		if (clazz.getAnnotation(CustomDomain.class) != null) {
			Method customDomainMethod;
			try {
				customDomainMethod = clazz.getMethod(CUSTOM_DOMAIN_NAME_METHOD, null);
				domain = (String) invokeMethod(customDomainMethod, null);
			} catch (NoSuchMethodException e) { 
				// TODO log and throw exception
			} catch (SecurityException e) { 
				// TODO log and throw exception
			}
		} 

		if (domain == null) {			
			String camelCaseString = clazz.getSimpleName();
			domain = StringUtil.toLowerFirstChar(camelCaseString);
		}

		ret.append(domain);

		return ret.toString();
	}
	
	private String getDomainPrefix(Class<?> clazz) {
		String prefix = null;
		DomainPrefix annotatedDomainPrefix = clazz.getAnnotation(DomainPrefix.class);
		if (annotatedDomainPrefix != null) {
			prefix = annotatedDomainPrefix.value();
		} else {
			prefix = this.domainPrefix;
		}

		return prefix;
	}
	
}