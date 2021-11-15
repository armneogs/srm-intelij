package dede.srm.util;

import java.util.function.Supplier;

public class ObjectCheckerUtil {
	public static <T> boolean isObjectPkNull(Object obj, Supplier<T> supplier) {
		if (obj == null) {
			return true;
		}

		T pk = supplier.get();

		return pk == null;

	}
}
