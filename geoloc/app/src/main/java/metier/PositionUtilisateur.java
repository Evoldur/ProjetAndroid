package metier;

/**
 * Created by thbrumter on 25/03/17.
 */

public class PositionUtilisateur {
    private String latitude;
    private String longitude;
    private String couleurMarker;

    public PositionUtilisateur() {
        latitude = "";
        longitude = "";
        couleurMarker = "";
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCouleurMarker() {
        return couleurMarker;
    }

    public void setCouleurMarker(String couleurMarker) {
        this.couleurMarker = couleurMarker;
    }

    public PositionUtilisateur(String latitude, String longitude, String couleurMarker) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.couleurMarker = couleurMarker;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PositionUtilisateur)) {
            return false;
        }

        PositionUtilisateur other = (PositionUtilisateur)obj;

        return (this.latitude.equals(other.latitude)
                && this.longitude.equals(other.longitude)
                && this.couleurMarker == other.couleurMarker);
    }
}
