package com.learnvest.challenge.input

trait Tranche{
 
    var rate    : Double = 0.0
    var balance : Double = 0.0
    var interest: Double = 0.0
    
    def isDebt: Boolean
    

  
    def applyInterest(cashflow: Double): Double
  
    def applyBalance(cashflow:Double): Double = {
        var x: Double = 0.0
        
        if (balance <  cashflow){
          x =  balance
          balance = 0.0
        } else {
          x = cashflow
          balance = balance - cashflow
        }
        return x
    }
}
