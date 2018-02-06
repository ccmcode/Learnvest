package com.learnvest.challenge.input

case class EquityTranche(faceValue: Double) extends Tranche{
    
    balance = faceValue
  
    def isDebt: Boolean = {return false}
    
    def applyInterest(cashflow: Double): Double = {return 0.0}
  
}