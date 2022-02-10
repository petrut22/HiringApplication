class InvalidDatesException extends Exception {
    public InvalidDatesException() {
        System.out.println("Data de inceput este mai mare decat data de final");
    }
}