package models

import java.io.{PrintWriter, File}
import scala.io.Source

trait FileStory {

  val path: String

  def name: String = new File(path).getName.split('.').init.mkString(".")

  def content: String = {
    if (path.isEmpty) { "" }
    else { Source.fromFile(path).mkString }
  }

  def rename(originalPath: String): Boolean = {
    new File(originalPath).renameTo(new File(path))
  }

  def save(content: String, overwrite: Boolean): Boolean = {
    val file = new File(path)
    if (file.exists != overwrite) {
      false
    } else {
      val writer = new PrintWriter(file)
      writer.write(content)
      writer.close()
      true
    }
  }


}
