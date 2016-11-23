package com.mbit.autorestop5word;

import java.util.Comparator;

import org.apache.hadoop.io.Text;

public class AutorPalabraOcurr {
  private long ocurr = 0;
  private Text autor_palabra = new Text();

  public AutorPalabraOcurr(long ocurr, Text autor_palabra) {
    super();
    this.ocurr = ocurr;
    this.autor_palabra = autor_palabra;
  }

  public long getOcurr() {
    return ocurr;
  }

  public void setOcurr(long ocurr) {
    this.ocurr = ocurr;
  }

  public Text getAutorPalabra() {
    return autor_palabra;
  }

  public void setAutorPalabra(Text autor_palabra) {
    this.autor_palabra = autor_palabra;
  }

  public static class AutorPalabraOcurrComparator implements
      Comparator<AutorPalabraOcurr> {

    @Override
    public int compare(AutorPalabraOcurr autopal1, AutorPalabraOcurr autopal2) {
      // TODO Auto-generated method stub
      return ((autopal2.getOcurr() >= autopal1.getOcurr()) ? ((autopal2
          .getOcurr() == autopal1
          .getOcurr()) ? 0 : 1) : -1);
    }

  }

}
