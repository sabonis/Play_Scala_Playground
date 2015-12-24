package controllers

import javax.inject.Inject

import dao.{Cat, CatDAO}
import play.api.Play
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

import scala.concurrent.Future

class Application @Inject()(catDao: CatDAO) extends Controller {

  def index = Action {
    //Ok(views.html.index("Your new application is ready."))
    Ok("Fuck world.")
    //Redirect(routes.Products.list())
  }

  def hello = Action {
    Ok("Fuck world.")
  }

  def test = Action {
    Ok(Play.current.configuration.getString("environment.user").get)
  }

  def testFuture = Action.async {
    val someShitNeedsWait = Future("shit")
    someShitNeedsWait map (Ok(_))
  }

  def insertCat() = Action.async {
    catDao.insert(Cat("kk" , "gg")).map(_ => Ok("ok"))
  }

  def testCatsDao = Action.async {
    catDao.all().map { cats => Ok(cats.toString) }
  }

}
