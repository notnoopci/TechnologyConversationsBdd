package models

import models.jbehave.JBehaveStory
import models.file.FileStory

class Story(val dir: String, val path: String) extends JBehaveStory with FileStory { }

object Story {

  def apply(dir: String, path: String): Story = new Story(dir, path)

}
