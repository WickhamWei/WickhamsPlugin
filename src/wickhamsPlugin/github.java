package wickhamsPlugin;

import com.google.gson.annotations.SerializedName;

public class Github {
	@SerializedName("published_at")
	private String originTimeString;

	@SerializedName("tag_name")
	private String versionString;

	@SerializedName("body")
	private String descriptionString;

	public String getLastVersion() {
		return versionString;
	}

	public String getLastVersionPublishedTime() {
		String[] all = originTimeString.split("T");
		String[] dateStrings = all[0].split("-");
		String[] time0Strings = all[1].split("Z");
		StringBuffer stringBuffer = new StringBuffer();
		for (String s:time0Strings) {
		    stringBuffer.append(s);
		}
		String[] timeStrings=stringBuffer.toString().split(":");
		int[] dateInt = new int[3];
		int[] timeInt = new int[3];
		for (int i = 0; i < dateInt.length; i++) {
			dateInt[i] = Integer.parseInt(dateStrings[i]);
			timeInt[i] = Integer.parseInt(timeStrings[i]);
		}
		if (timeInt[0] + 8 < 24) {
			timeInt[0] = timeInt[0] + 8;
		} else {
			timeInt[0] = timeInt[0] + 8 - 24;
			if (dateInt[2] < 28) {
				dateInt[2] = dateInt[2] + 1;
			} else {
				if (dateInt[1] != 2 && dateInt[2] < 30) {
					dateInt[2] = dateInt[2] + 1;
				} else {
					if (dateInt[1] == 1 || dateInt[1] == 3 || dateInt[1] == 5 || dateInt[1] == 7 || dateInt[1] == 8
							|| dateInt[1] == 10 || dateInt[1] == 12) {
						if (dateInt[2] + 1 < 32) {
							dateInt[2] = dateInt[2] + 1;
						} else {
							if (dateInt[1] + 1 < 13) {
								dateInt[1] = dateInt[1] + 1;
								dateInt[2] = 1;
							} else {
								dateInt[1] = 1;
								dateInt[0] = dateInt[0] + 1;
								dateInt[2] = 1;
							}
						}
					} else {
						if (dateInt[1] != 2) {
							if (dateInt[2] + 1 < 31) {
								dateInt[2] = dateInt[2] + 1;
							} else {
								if (dateInt[1] + 1 < 13) {
									dateInt[1] = dateInt[1] + 1;
									dateInt[2] = 1;
								} else {
									dateInt[1] = 1;
									dateInt[0] = dateInt[0] + 1;
									dateInt[2] = 1;
								}
							}
						} else {
							if (dateInt[0] % 4 == 0 && dateInt[0] % 100 != 0 || dateInt[0] % 400 == 0) {
								if (dateInt[2] + 1 < 30) {
									dateInt[2] = dateInt[2] + 1;
								} else {
									if (dateInt[1] + 1 < 13) {
										dateInt[1] = dateInt[1] + 1;
										dateInt[2] = 1;
									} else {
										dateInt[1] = 1;
										dateInt[0] = dateInt[0] + 1;
										dateInt[2] = 1;
									}
								}
							} else {
								if (dateInt[2] + 1 < 29) {
									dateInt[2] = dateInt[2] + 1;
								} else {
									if (dateInt[1] + 1 < 13) {
										dateInt[1] = dateInt[1] + 1;
										dateInt[2] = 1;
									} else {
										dateInt[1] = 1;
										dateInt[0] = dateInt[0] + 1;
										dateInt[2] = 1;
									}
								}
							}
						}
					}
				}
			}
		}
		String formalTimeString = new String(
				String.valueOf(dateInt[0]) + "年" + String.valueOf(dateInt[1]) + "月" + String.valueOf(dateInt[2]) + "日"
						+ String.valueOf(timeInt[0]) + "时" + String.valueOf(timeInt[1]) + "分");
		return formalTimeString;
	}

	public String getLastVersionDescription() {
		return descriptionString;
	}

	// 2019-05-27T05:48:40Z
}
