package com.learnvest.challenge
import com.learnvest.challenge.input.{Collateral, Tranche}
import com.learnvest.challenge.output.{Output, DebtTrancheOutput, EquityTrancheOutput}


/**
  * Not a working implementation, only used for sample test.
  */
class DummyScalaImplementation extends ScalaChallengeImplementation {
  override def modelCDO(tranches: List[Tranche], collateral: Collateral): Map[Tranche, List[Output]] = {
      
      var i: Int = 1
      var cashflow: Double = 0.0
      var payment : Double = 0.0
      
      var out  = scala.collection.mutable.Map[Tranche, List[Output]]().withDefaultValue(List())
      var map  = scala.collection.mutable.Map[Tranche, Double]()
       
      while(collateral.moreTerms()){
          println("Year " + i)
          cashflow = collateral.getPayment()
          println(cashflow)
          
          for (t <- tranches){
              if (t.isDebt){
                  payment = t.applyInterest(cashflow)
                  cashflow = cashflow - payment
                  map += (t -> payment)
              }
          }
          
          for (t <- tranches){
              payment = t.applyBalance(cashflow)
              cashflow = cashflow - payment
              
              if (t.isDebt){
                  out(t) = out(t):+DebtTrancheOutput(t.balance+payment, t.interest, map(t)+payment, t.balance)
              } else{
                 out(t) = out(t):+EquityTrancheOutput(payment)
              }
          }
          i += 1
      }
   
      return out.toMap
  }
}
