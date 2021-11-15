package dede.srm.util;

import java.util.ArrayList;
import java.util.List;


public class TypeConvertionUtil {
	public static <Clazz> List<Clazz> convertIterableToList(Iterable<Clazz> Iterable) {
		List<Clazz> list = new ArrayList<Clazz>();
		if (Iterable == null)
			return list;

		for (Clazz obj : Iterable) {
			list.add(obj);
		}

		return list;

	}
}