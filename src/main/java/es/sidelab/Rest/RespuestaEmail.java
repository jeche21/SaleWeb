package es.sidelab.Rest;

public class RespuestaEmail {
    boolean ok;
    String message;

    public RespuestaEmail(boolean ok, String message) {
        this.ok = ok;
        this.message = message;
    }
    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
