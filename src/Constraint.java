class Constraint {
    //am folosit tipul Double pentru a usura comparatiile pentru meanGPA
    Double inferior;
    Double superior;
    //constructorul folosit pentru instantierea limitelor
    public Constraint(Double inferior, Double superior) {
        this.inferior = inferior;
        this.superior = superior;
    }

    public Double getInferior() {
        return inferior;
    }

    public Double getSuperior() {
        return superior;
    }
}