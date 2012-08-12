package org.lc0277lib.geography;

import java.text.*;
import java.util.*;


/**
 * A formatter for latitude and longitudes
 * @author lc
 *
 */
public abstract class FormatLatLon extends Format {
	
	public static final DecimalFormat DF0 = 
		new DecimalFormat("#########0", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
	public static final DecimalFormat DF1 = 
		new DecimalFormat("#########0.0", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
	public static final DecimalFormat DF2 = 
		new DecimalFormat("#########0.00", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
	public static final DecimalFormat DF3 = 
		new DecimalFormat("#########0.000", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); //$NON-NLS-1$
	public static final DecimalFormat DF6 = 
		new DecimalFormat("#########0.000000", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); 
	public static final DecimalFormat DF6_LOCALE = 
		new DecimalFormat("#########0.000000");//$NON-NLS-1$

	public static final DecimalFormat DF8 = 
		new DecimalFormat("#########0.00000000", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); 
	public static final DecimalFormat DF8_LOCALE = 
		new DecimalFormat("#########0.00000000");//$NON-NLS-1$

	public static final DecimalFormat DF10 = 
		new DecimalFormat("#########0.0000000000", //$NON-NLS-1$
				new DecimalFormatSymbols(Locale.ENGLISH)); 
	public static final DecimalFormat DF10_LOCALE = 
		new DecimalFormat("#########0.0000000000");//$NON-NLS-1$

	public enum FormatType {
		/** degrees, minutes decimal (N42 42.3) */
		DMDEC,
		/** degrees, minutes seconds (N43 43' 4) */
		DMS,
		/** decimal 42.71 */
		DECIMAL,
		/** decimal local 42,71 */
		DECIMAL_LOCAL,
		/** decimal, with 8 decimal digits */
		DECIMAL8,
		/** decimal with 10 decimal digits */
		DECIMAL10;
		
		
		private static String[] STRINGS = 
		{
			"Degrees and decimal minutes",
			"Degrees, minutes, seconds",
			"Decimal degrees",
			"Decimal degrees (local format)",
			"Decimal degrees (with 8 digits)",
			"Decimal degrees (with 10 digits)"
		};
		
		static final int SIZE = 6;
		
		@Override
		public String toString() {
			return STRINGS[ordinal()];
		}
	}
	
	/* **************************************************************
	 * 
	 * The formatter factory sStaticly memorize some formatters, 
	 * so you don't need to allocate a new one each time
	 * 
	 * **************************************************************
	 */
	
	private static Format[] formatters = new Format[2 * FormatType.SIZE];
	
	public static Format getLatitudeFormatter(FormatType type) {
		if (type == null) type = FormatType.DECIMAL;
		int idx = type.ordinal();
		if (formatters[idx] == null) {
			formatters[idx] = new LatitudeFormatter(type);
		}
		return formatters[idx];
	}
	
	public static Format getLongitudeFormatter(FormatType type) {
		if (type == null) type = FormatType.DECIMAL;
		int idx = FormatType.SIZE + type.ordinal();
		if (formatters[idx] == null) {
			formatters[idx] = new LongitudeFormatter(type);
		}
		return formatters[idx];
	}
	
	
	
	
	/* **************************************************************
	 * 
	 * Formatting
	 * 
	 * **************************************************************
	 */
	
	public static String formatLatDMDEC(double latRadians) {
		StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
		formatLatDMDEC(sgb, latRadians);
		return sgb.toString();
	}
	
	static void formatLatDMDEC(StringBuffer sgb, double latRadians) {
		double latDegrees = Math.toDegrees(latRadians);
		if (latDegrees < 0) {
			sgb.append('S');
			latDegrees = Math.abs(latDegrees);
		}
		else {
			sgb.append('N');
		}
		double deg = Math.floor(latDegrees);
		double min = (latDegrees - deg) * 60.0;
		// round to 59.99
		min = (double)((int) (min * 1000)) / 1000;
		sgb.append(DF0.format(deg));
		sgb.append(" "); //$NON-NLS-1$
		sgb.append(DF3.format(min));
//		sgb.append("'"); //$NON-NLS-1$
		
	}
	
	public static String formatLatDMS(double latRadians) {
		StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
		formatLatDMS(sgb, latRadians);
		return sgb.toString();
	}

	static void formatLatDMS(StringBuffer sgb, double latRadians) {
		double latDegrees = Math.toDegrees(latRadians);
		if (latDegrees < 0) {
			sgb.append('S');
			latDegrees = Math.abs(latDegrees);
		}
		else {
			sgb.append('N');
		}
		double deg = Math.floor(latDegrees);
		double min = Math.floor((latDegrees - deg) * 60.0);
		double sec = ((latDegrees - deg - min/60.0) * 3600);
		
		// round to 59.99
		min = (double)((int) (min * 100)) / 100;
		
		sgb.append(DF0.format(deg));
		sgb.append("°"); //$NON-NLS-1$
		sgb.append(DF0.format(min));
		sgb.append("'"); //$NON-NLS-1$
		sgb.append(DF3.format(sec));
		
	}

	
	public static String formatLonDMDEC(double lonRadians) {
		StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
		formatLonDMDEC(sgb, lonRadians);
		return sgb.toString();
	}
	
	static void formatLonDMDEC(StringBuffer sgb, double lonRadians) {
		double latDegrees = Math.toDegrees(lonRadians);
		if (latDegrees < 0) {
			sgb.append('W');
			latDegrees = Math.abs(latDegrees);
		}
		else {
			sgb.append('E');
		}
		double deg = Math.floor(latDegrees);
		double min = (latDegrees - deg) * 60.0;
		// round to 59.99
		min = (double)((int) (min * 1000)) / 1000;
		sgb.append(DF0.format(deg));
		sgb.append(" "); //$NON-NLS-1$
		sgb.append(DF3.format(min));
//		sgb.append("'"); //$NON-NLS-1$
	}
	
	public static String formatLonDMS(double lonRadians) {
		StringBuffer sgb = new StringBuffer(""); //$NON-NLS-1$
		formatLonDMS(sgb, lonRadians);
		return sgb.toString();
	}
	
	static void formatLonDMS(StringBuffer sgb, double lonRadians) {
		double latDegrees = Math.toDegrees(lonRadians);
		if (latDegrees < 0) {
			sgb.append('W');
			latDegrees = Math.abs(latDegrees);
		}
		else {
			sgb.append('E');
		}
		
		double deg = Math.floor(latDegrees);
		double min = Math.floor((latDegrees - deg) * 60.0);
		double sec = ((latDegrees - deg - min/60.0) * 3600);
		
		// round to 59.99
		min = (double)((int) (min * 100)) / 100;
		sgb.append(DF0.format(deg));
		sgb.append("°"); //$NON-NLS-1$
		sgb.append(DF0.format(min));
		sgb.append("'"); //$NON-NLS-1$
		sgb.append(DF3.format(sec));
	}

	public static String formatLonDecimal(double lonRadians) {
		return DF6.format(Math.toDegrees(lonRadians));
	}
	
	public static String formatLatDecimal(double latRadians) {
		return DF6.format(Math.toDegrees(latRadians));
	}

	public static String formatLonDecimal8(double lonRadians) {
		return DF8.format(Math.toDegrees(lonRadians));
	}
	
	public static String formatLatDecimal8(double latRadians) {
		return DF8.format(Math.toDegrees(latRadians));
	}
	

	private Vector<String> tokenize(char cp[]) {
		String cur = ""; //$NON-NLS-1$
		Vector<String> tokens = new Vector<String>();
		
		int i = 0;
		while (i < cp.length) {
			char c = cp[i++];
			
			switch (c) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case '.':
			case '-':
				cur += c;
				break;
				
			case ' ':
				if (cur.length() > 0) tokens.add(cur);
				cur = ""; //$NON-NLS-1$
				break;
			
			default:
				if (cur.length() > 0) tokens.add(cur);
				cur = "" + c; //$NON-NLS-1$
				tokens.add(cur);
				cur = ""; //$NON-NLS-1$
				break;
			}
		}
		if (cur.length() > 0) tokens.add(cur);
		
		return tokens;
	}
	
	
	private double parse(String source) {
		Vector<String> tokens = tokenize(source.toCharArray());
		Iterator<String> its = tokens.iterator();
/*		
		for (String s : tokens) {
			System.out.println("TOK '" + s + "'");
		}
*/	
		double d;
		boolean isInvert = false;
		String s = its.next().toUpperCase();
		if (s.equals("W") || //$NON-NLS-1$
				s.equals("S")) { //$NON-NLS-1$
			isInvert = true;
			d = Double.parseDouble(its.next());
		} else if (s.equals("N") || "E".equals(s)) { //$NON-NLS-1$ //$NON-NLS-2$
			d = Double.parseDouble(its.next());
		} else {
			d = Double.parseDouble(s);
		}
		
		if (its.hasNext()) {
			s = its.next();
			if ("*".equals(s)) { //$NON-NLS-1$
				//skip
				if (its.hasNext()) s = its.next();
				else s = "0"; //$NON-NLS-1$
			}
			if ("°".equals(s)) { //$NON-NLS-1$
				//skip
				if (its.hasNext()) s = its.next();
				else s = "0"; //$NON-NLS-1$
			}
			
			d += (Double.parseDouble(s) / 60.0);
		}
		
		if (its.hasNext()) {
			s = its.next();
			if ("'".equals(s)) { //$NON-NLS-1$
				//skip
				if (its.hasNext()) s = its.next();
				else s = "0"; //$NON-NLS-1$
			}
			
			d += (Double.parseDouble(s) / 3600.0);
		}
		
		if (isInvert) d = -d;
		return Math.toRadians(d);
	}

	@Override
	public Object parseObject(String source, ParsePosition pos) {
		source = source.substring(pos.getIndex());
		
		try {
			double d = parse(source);
			pos.setIndex(source.length());
			return d;
		} catch (Exception e) {}
		pos.setErrorIndex(pos.getIndex());
		return null;
	}
	
	/*
	public Object parseObject(String source, ParsePosition pos) {
		source = source.substring(pos.getIndex());
		Matcher m = COORD_PARSER.matcher(source);
		if (!m.matches()) {
			pos.setErrorIndex(pos.getIndex());
			return null;
		}
		
		double d = 0.0;
		try {
			d += Integer.parseInt(m.group(2).trim());
			d += Double.parseDouble(m.group(3).trim()) / 60;
			
			char c = m.group(1).charAt(0);
			if (c == 'S' || c == 'W') d = -d;
		} catch (NumberFormatException nfe) {
			// error
			pos.setErrorIndex(pos.getIndex());
			return null;
		}
		
		pos.setIndex(source.length());
		return d;
	}
	*/
}


@SuppressWarnings("serial") //$NON-NLS-1$
class LongitudeFormatter extends FormatLatLon {

	private FormatType type;
	
	LongitudeFormatter(FormatType type) {
		this.type = type;
	}
	
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
			Number nn = (Number) obj;
			double lonRadians = nn.doubleValue();
			switch (type) {
			case DECIMAL10:
				FormatLatLon.DF10.format(Math.toDegrees(lonRadians), toAppendTo, pos);
				break;
			case DECIMAL8:
				FormatLatLon.DF8.format(Math.toDegrees(lonRadians), toAppendTo, pos);
				break;
			case DECIMAL:
				FormatLatLon.DF6.format(Math.toDegrees(lonRadians), toAppendTo, pos);
				break;
			case DECIMAL_LOCAL:
				FormatLatLon.DF6_LOCALE.format(Math.toDegrees(lonRadians), toAppendTo, pos);
				break;
			case DMDEC:
				FormatLatLon.formatLonDMDEC(toAppendTo, lonRadians);
				break;
			case DMS:
				FormatLatLon.formatLonDMS(toAppendTo, lonRadians);
				break;
			}
			return toAppendTo;
	}
}



@SuppressWarnings("serial") //$NON-NLS-1$
class LatitudeFormatter extends FormatLatLon {
	
	private FormatType type;

	LatitudeFormatter(FormatType type) {
		this.type = type;
	}
	
	@Override
	public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
		Number nn = (Number) obj;
		double latRadians = nn.doubleValue();
		switch (type) {
		case DECIMAL10:
			FormatLatLon.DF10.format(Math.toDegrees(latRadians), toAppendTo, pos);
			break;
		case DECIMAL8:
			FormatLatLon.DF8.format(Math.toDegrees(latRadians), toAppendTo, pos);
			break;
		case DECIMAL:
			FormatLatLon.DF6.format(Math.toDegrees(latRadians), toAppendTo, pos);
			break;
		case DECIMAL_LOCAL:
			FormatLatLon.DF6_LOCALE.format(Math.toDegrees(latRadians), toAppendTo, pos);
			break;
		case DMDEC:
			FormatLatLon.formatLatDMDEC(toAppendTo, latRadians);
			break;
		case DMS:
			FormatLatLon.formatLatDMS(toAppendTo, latRadians);
			break;
		}
		return toAppendTo;
	}
	
	
}
