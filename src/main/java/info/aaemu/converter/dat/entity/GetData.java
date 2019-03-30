package info.aaemu.converter.dat.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import info.aaemu.converter.dat.gui.ErrorPage;

public class GetData {

	private Map<String, DatStructure> map;

	private String fileName;
	private String datByteString;
	private String byteString;
	private Path jsonString;

	private String stringId;
	private int id;
	private String x;
	private String y;
	private String z;

	public void convert(Path datFile) throws IOException {
		createJson(datFile);
		map = new HashMap<String, DatStructure>();
		datByteString = "";
		FileInputStream in = new FileInputStream(datFile.toFile());
		int read;
		while ((read = in.read()) != -1) {
			if ((Integer.toHexString(read)).length() == 1) {
				byteString = "0" + Integer.toHexString(read);
			} else {
				byteString = Integer.toHexString(read);
			}
			datByteString = datByteString + "" + byteString;
		}

		in.close();
		int startPos = 0;
		try {
			while (startPos < datByteString.length()) {
				stringId = datByteString.substring(startPos, startPos + 4);
				x = datByteString.substring(startPos + 8, startPos + 16);
				y = datByteString.substring(startPos + 16, startPos + 24);
				z = datByteString.substring(startPos + 24, startPos + 32);
				startPos = startPos + 32;
				id = convertId(stringId);
				x = convertCoordinates(x);
				y = convertCoordinates(y);
				z = convertCoordinates(z);
				map.put(String.valueOf(map.size() + 1), new DatStructure(id, x, y, z));
			}
		} catch (Exception e) {
			new ErrorPage();
		}
		writeJson(datFile);
	}

	private void writeJson(Path datFile) throws IOException {
		jsonString = Paths.get(datFile.getParent().toString() + "\\" + fileName).toAbsolutePath();
		Files.write(jsonString, new JSONObject(map).toString().getBytes());
	}

	private void createJson(Path datFile) {
		fileName = datFile.getFileName().toString();
		fileName = fileName.replace(".dat", ".json");
	}

	private int convertId(String stringId) {
		stringId = stringId.substring(2, 4) + stringId.substring(0, 2);
		return Integer.parseInt(stringId, 16);
	}

	private String convertCoordinates(String coordinate) {
		coordinate = coordinate.substring(6, 8) + coordinate.substring(4, 6) + coordinate.substring(2, 4)
				+ coordinate.substring(0, 2);
		Long longId = Long.parseLong(coordinate, 16);
		Float floatId = Float.intBitsToFloat(longId.intValue());
		return new DecimalFormat("##.##########").format(floatId);
	}

}
