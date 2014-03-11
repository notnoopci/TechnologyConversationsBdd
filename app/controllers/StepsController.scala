package controllers

import play.api.mvc.{Controller, AnyContent, Action}
import models.JBehaveSteps

object StepsController extends Controller {

  def listJson: Action[AnyContent] = Action {
    Ok(JBehaveSteps().toJson)
  }

}