package com.learnvest.challenge.input
import scala.math.pow
/**
  * Input object representing the underlying collateral whose cash flow is being used to as input into the CDO.
  * For purposes of this project you can think of the collateral and a group of mortgages all aggregated together.
  * @param beginningBalance - Total pricipal balance of all the mortgaes in the collateral pool.
  * @param numberOfYears - Term length of the loans. 30 yrs, 15 yrs ect.
  * @param rateOfReturn - The interest rate paid on the mortgages. Will be passed in as a decimal value .05 for 5% for example.
  * @param prePaymentRate - The rate at which the mortgages are paid down. Passed in as a decimal. The rate represents a
  *                       percentage of the total principal balance that is paid early due to people refinancing or selling their homes.
  */
case class Collateral(beginningBalance: Double,
                      numberOfYears: Int,
                      rateOfReturn: Double,
                      prePaymentRate: Double
                     ){
  
    var balance: Double =  beginningBalance
    var years: Int      =  numberOfYears
    val rrate: Double   =  rateOfReturn
    val prate: Double   =  prePaymentRate
    
    def getPayment(): Double = {
        var payment = balance*prate
        payment = payment + (balance*rrate*pow(1.0+rrate,years.toDouble) / (pow(1.0+rrate,years.toDouble)-1))
        
        if (payment > balance){
          payment = balance
        }
        
        balance = balance - payment
        years = years - 1
        
        return payment
    }
    
  def moreTerms(): Boolean = {
    return years > 0 || balance > 0
  }
}
