package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

public class Pengiriman {
    private UUID id = UUID.randomUUID();
    private UUID idPenjual;
    private String idTransaksi;
    private boolean isSent;
    private boolean isArrived;
    private LocalDate timeStamp = LocalDate.now();

    public void send() {
        this.isSent = true;
    }

    public void setIdPenjual(UUID penjual) {
        this.idPenjual = penjual;
    }

    public UUID getIdPenjual() {
        return this.idPenjual;
    }

    public void nextDay() {
        this.isArrived = true;
        this.isSent = false;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isIsSent() {
        return this.isSent;
    }

    public boolean getIsSent() {
        return this.isSent;
    }

    public void setIsSent(boolean isSent) {
        this.isSent = isSent;
    }

    public boolean isIsArrived() {
        return this.isArrived;
    }

    public boolean getIsArrived() {
        return this.isArrived;
    }

    public void setIsArrived(boolean isArrived) {
        this.isArrived = isArrived;
    }

    public void setTimeStamp(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", new Locale("id"));
        String formattedDate = date.format(formatter);
        this.timeStamp = date;
    }

    public LocalDate getTimeStamp() {
        return this.timeStamp;
    }

    public void setIdTransaksi(int urutan) {
        String formatted = this.timeStamp.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.idTransaksi = "TRX" + formatted + String.format("%03d", urutan);
    }

    public String getIdTransaksi() {
        return this.idTransaksi;
    }

}