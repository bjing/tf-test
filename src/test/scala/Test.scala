import java.io.File
// import java.io.IOException
import java.nio.file.Files
import org.tensorflow.Graph

import org.scalatest.{FreeSpec, Matchers}

class JsonUtilsTest extends FreeSpec with Matchers {

  "example3"  - {
    "can load graph file" in {
      val modelFile = new File("/tmp/saved_model_1/model.pb")
      val graphDef = Files.readAllBytes(modelFile.toPath)
      val graph = new Graph()
      graph.importGraphDef(graphDef)
      println(graph)
      assert(true)
    }
  }
}
