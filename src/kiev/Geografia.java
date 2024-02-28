package kiev;

import java.util.ArrayList;

import grafoPonderado.Estacion;

public class Geografia {
	
	// De Internet sale este valor
	private static final double kievMetroSpeed = 10.03056;
	
	/*
	 * https://en.wikipedia.org/wiki/Haversine_formula
	 * @returns distancia entre 2 lats y 2 longs en metros
	 */
	public static double distanciaEntre2Puntos(double lat1, double lon1, double lat2, double lon2) {
		
		double R, dLat, dLon, a, c, d;
		
		R = 6378.137;
		dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
		dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
		
		a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
			    Math.sin(dLon/2) * Math.sin(dLon/2);
		
		c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    d = R * c;
	    
	    return d * 1000;
		
		
//		double latMid, m_per_deg_lat, m_per_deg_lon, deltaLat, deltaLon, dist_m;
//
//		latMid = (lat1+lat2 )/2.0;  // or just use Lat1 for slightly less accurate estimate
//
//
//		m_per_deg_lat = 111132.954 - 559.822 * Math.cos( 2.0 * latMid ) + 1.175 * Math.cos( 4.0 * latMid);
//		m_per_deg_lon = (3.14159265359/180 ) * 6367449 * Math.cos ( latMid );
//
//		deltaLat = Math.abs(lat1 - lat2);
//		deltaLon = Math.abs(lon1 - lon2);
//
//		return dist_m = Math.sqrt (  Math.pow( deltaLat * m_per_deg_lat,2) + Math.pow( deltaLon * m_per_deg_lon , 2) );
		
//		return Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(lon1- lon2, 2)) * 111111;
		
//		double R = 6371; // km
//		double zeta = (lat1 + lat2) / 2;
//		double phi = (lon1 + lon2) / 2;
//		
//		double d = R* Math.sqrt(Math.pow(lat1 - lat2, 2) + Math.pow(Math.cos(zeta),2)*Math.pow(lon1 - lon2, 2));
//		
//		return d;
		
	}
	
	
	/*
	 * @returns una distancia en metros
	 */
	public static double getDistancia(ArrayList<Estacion> trayecto) {
		double res = 0;
		for (int i = 0; i < trayecto.size()-1; i++) {
			res += distanciaEntre2Puntos(trayecto.get(i).latitud, trayecto.get(i).longitud,
					trayecto.get(i+1).latitud, trayecto.get(i+1).longitud);
		}
		return res;
	}
	
	/*
	 * Asumimos que el metro se para unos 45 segundos en cada parada/estación.
	 * @returns un tiempo en segundos.
	 */
	public static double getTiempo(double distancia, int numeroParadas) {
		return distancia/kievMetroSpeed + numeroParadas*45;
	}
	
}
