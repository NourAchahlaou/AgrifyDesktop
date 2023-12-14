
package agrify.entities;

import java.util.Date;


public class Reclamation {
       private int rec_id;
    private String rec_emp;
    private Date rec_date;

    private TypeRec typeReclamation;
    private String rec_description;
   
    private String rec_target;
    private String urgency;

    public Reclamation() {
    }

    public Reclamation(String rec_emp, Date rec_date, TypeRec typeReclamation, String rec_description, String rec_target, String urgency) {
        this.rec_emp = rec_emp;
        this.rec_date = rec_date;
        this.typeReclamation = typeReclamation;
        this.rec_description = rec_description;
        this.rec_target = rec_target;
        this.urgency = urgency;
    }

    public Reclamation(int rec_id, String rec_emp, Date rec_date, TypeRec typeReclamation, String rec_description, String rec_target, String urgency) {
        this.rec_id = rec_id;
        this.rec_emp = rec_emp;
        this.rec_date = rec_date;
        this.typeReclamation = typeReclamation;
        this.rec_description = rec_description;
        this.rec_target = rec_target;
        this.urgency = urgency;
    }

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getRec_emp() {
        return rec_emp;
    }

    public void setRec_emp(String rec_emp) {
        this.rec_emp = rec_emp;
    }

    public Date getRec_date() {
        return rec_date;
    }

    public void setRec_date(Date rec_date) {
        this.rec_date = rec_date;
    }

    public TypeRec getTypeReclamation() {
        return typeReclamation;
    }

    public void setTypeReclamation(TypeRec typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public String getRec_description() {
        return rec_description;
    }

    public void setRec_description(String rec_description) {
        this.rec_description = rec_description;
    }

    public String getRec_target() {
        return rec_target;
    }

    public void setRec_target(String rec_target) {
        this.rec_target = rec_target;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "rec_id=" + rec_id + ", rec_emp=" + rec_emp + ", rec_date=" + rec_date + ", typeReclamation=" + typeReclamation + ", rec_description=" + rec_description + ", rec_target=" + rec_target + ", urgency=" + urgency + '}';
    }

   
    
}
