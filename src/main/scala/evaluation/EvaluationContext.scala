package evaluation

import semantic.Expr

/**
 * Контекст вычисления.
 */
trait EvaluationContext {
  /**
   * Состояние вычисления.
   */
  case class State(isInterpreting: Boolean, switchCondition: Option[Expr], isBreakExecuted: Boolean)

  private val context = new collection.mutable.Stack[State]()

  /**
   * Флаг интерпретации.
   */
  var isInterpreting = true

  /**
   * Условие switch
   */
  var switchCondition: Option[Expr] = None

  /**
   * Флаг вызова break.
   */
  var isBreakExecuted = false

  def saveContext(): Unit = {
    context.push(State(isInterpreting, switchCondition, isBreakExecuted))
  }

  def restoreContext(): Unit = {
    val ctx = context.pop()

    isInterpreting = ctx.isInterpreting
    switchCondition = ctx.switchCondition
    isBreakExecuted = ctx.isBreakExecuted
  }

  def peekContext(): State = context.top
}
