package com.learnvest.challenge.input

case class DebtTranche(initialPrincipal: Double,
                       rateOfReturn:Double) extends Tranche{
  
  balance  = initialPrincipal
  rate     = rateOfReturn
  interest = balance*rate
  
  
  def isDebt: Boolean = {return true}
  
  def applyInterest(cashflow:Double): Double = {   
      
      if (interest > cashflow) {  
        return cashflow
      } else {
        return interest
      }
  }
  
}
