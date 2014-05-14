package com.intactile.serialiser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CsvParser {
	String path;
	String deliminator = "\t";
	BufferedReader br = null;
	List<TimedFeature> timedFeaturelst = new LinkedList<TimedFeature>();

	public CsvParser(String filename, String deliminator) {
		path = filename;
		this.deliminator = deliminator;
	}

	public List<TimedFeature> getTimedFeature() {
		return timedFeaturelst;
	}

	public List<TimedFeature> parse() {

		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			String[] strs = null;
			TimedFeature timedFeature = new TimedFeature();
			while ((sCurrentLine = br.readLine()) != null) {
				strs = sCurrentLine.split(deliminator);
				TimedPoint tpoint = new TimedPoint();
				if (strs.length <= 4) {
					timedFeature.featureIdS = strs[0];
					timedFeature.featureName = strs[1];
					timedFeature.featureType = strs[2];
					timedFeature.featureDesc = strs[3];
					timedFeaturelst.add(timedFeature);

				} else if (strs.length > 4) {
					tpoint.tPointIdS = strs[0];
					tpoint.tPointLatitude = strs[1];
					tpoint.tPointLongitude = strs[2];
					tpoint.tPointAltitude = strs[3];
					tpoint.tPointDirection = strs[4];
					tpoint.tPointSpeed = strs[5];
					tpoint.tPointTime = strs[6];
					timedFeature.tFeatureWay.wayTLine.addWayPoint(tpoint);
				}

				/*
				 * for (int i = 0; i < strs.length; i++) {
				 * System.err.print(strs[i] + " - "); } System.err.println();
				 */
				// }
			}
			if (!timedFeature.tFeatureWay.wayTLine.points.isEmpty())
				timedFeature.lastPosition = timedFeature.tFeatureWay.wayTLine.points
						.get(timedFeature.tFeatureWay.wayTLine.points.size() - 1);
			//System.out.println("LastPosition: " + timedFeature.lastPosition);

			System.out.println("Points:");
			for (TimedPoint point : timedFeature.tFeatureWay.wayTLine.points) {
				System.out.println("Point dedans: " + point);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		// System.out.println("ListItem:" + lst);
		return timedFeaturelst;
	}

}
