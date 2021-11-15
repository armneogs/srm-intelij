package dede.srm.util;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class PropertyUtil {

	public static LinkedHashSet<String> getAllPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		LinkedHashSet<String> set = new LinkedHashSet<>();
		for (java.beans.PropertyDescriptor pd : pds) {
			set.add(pd.getName());
		}

		return set;
	}

	public static Set<String> getNullPropertyNames(Object source) {

		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		return emptyNames;
	}

	public static String[] getNullPropertyNameArray(Object source) {
		Set<String> nullPropSet = getNullPropertyNames(source);
		String[] nullPropArray = new String[nullPropSet.size()];
		int i = 0;
		for (String nullProp : nullPropSet) {
			nullPropArray[i++] = nullProp;
		}
		return nullPropArray;
	}

	public static String[] getNullPropertyArray(Object source) {
		return getIgnorePropertyArray(source, getNullPropertyNames(source));
	}

	public static String[] getIgnorePropertyArray(Object source, Set<String> ignoreProperty) {
		if (ignoreProperty == null) {
			return null;
		}
		String[] nullPropArray = new String[ignoreProperty.size()];
		int i = 0;
		for (String nullProp : ignoreProperty) {
			nullPropArray[i++] = nullProp;
		}
		return nullPropArray;
	}

}