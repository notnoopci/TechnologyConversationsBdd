package models.jbehave

import java.io.File
import play.api.libs.json.{Json, JsValue}
import scala.xml.XML

class JBehaveReporter {

  def listJson(reportsPath: String, id: String): Option[JsValue] = {
    val reports = list(reportsPath, id)
    if (reports.isEmpty) None
    else {
      val reportsMap = reports.get.map { report =>
        val reportPath = s"$reportsPath/$id/$report"
        Map(
          "path" -> Json.toJson(s"/$reportPath".replace("/public/", "/assets/")),
          "steps" -> Json.toJson(steps(reportPath.replace(".html", ".xml")))
        )
      }
      Some(Json.toJson(reportsMap))
    }
  }

  private[jbehave] def list(reportsPath: String, id: String): Option[List[String]] = {
    val dir = new File(s"$reportsPath/$id")
    if (!dir.exists()) None
    else Some(dir.listFiles()
      .filter(_.getName.endsWith(".html"))
      .sortWith(_.lastModified() < _.lastModified())
      .map(file => file.getName)
      .toList
    )
  }

  private[jbehave] def steps(reportPath: String) = {
    val xml = XML.loadFile(new File(reportPath))
    (xml \ "scenario" \ "step").map(node => Map(
      "text" -> node.text,
      "status" -> (node \ "@outcome").text
    ))
  }

}

object JBehaveReporter {

  def apply() = { new JBehaveReporter }

}
