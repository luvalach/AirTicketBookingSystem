/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa036.airticketbooking.api.dto;

/**
 *
 * @author Tommy
 */
public class SeatDto {
    
    public SeatDto() {}

    public SeatDto(Long id, PlaneDto airplane, Long number, Character nextToWindow, Character nextToAisle, Character inTheMiddle, Character firstClass, Character secondClass, Character businessClass, Character economyClass, Character disabledSeating) {
        this.id = id;
        this.airplane = airplane;
        this.number = number;
        this.nextToWindow = nextToWindow;
        this.nextToAisle = nextToAisle;
        this.inTheMiddle = inTheMiddle;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
        this.businessClass = businessClass;
        this.economyClass = economyClass;
        this.disabledSeating = disabledSeating;
    }
    
    private Long id;
    private PlaneDto airplane;
    private Long number;
    private Character nextToWindow;
    private Character nextToAisle;
    private Character inTheMiddle;
    private Character firstClass;
    private Character secondClass;
    private Character businessClass;
    private Character economyClass;
    private Character disabledSeating;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlaneDto getAirplane() {
        return airplane;
    }

    public void setAirplane(PlaneDto airplane) {
        this.airplane = airplane;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Character getNextToWindow() {
        return nextToWindow;
    }

    public void setNextToWindow(Character nextToWindow) {
        this.nextToWindow = nextToWindow;
    }

    public Character getNextToAisle() {
        return nextToAisle;
    }

    public void setNextToAisle(Character nextToAisle) {
        this.nextToAisle = nextToAisle;
    }

    public Character getInTheMiddle() {
        return inTheMiddle;
    }

    public void setInTheMiddle(Character inTheMiddle) {
        this.inTheMiddle = inTheMiddle;
    }

    public Character getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(Character firstClass) {
        this.firstClass = firstClass;
    }

    public Character getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(Character secondClass) {
        this.secondClass = secondClass;
    }

    public Character getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(Character businessClass) {
        this.businessClass = businessClass;
    }

    public Character getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(Character economyClass) {
        this.economyClass = economyClass;
    }

    public Character getDisabledSeating() {
        return disabledSeating;
    }

    public void setDisabledSeating(Character disabledSeating) {
        this.disabledSeating = disabledSeating;
    }
    
    
}
