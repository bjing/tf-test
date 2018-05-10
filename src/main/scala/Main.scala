import java.nio.FloatBuffer
import java.nio.file.{Files, Paths}

import scala.collection.JavaConverters._
import org.tensorflow.{Graph, Session, Tensor}

object Main extends App {
  val graph = loadTfModel("/tmp/saved_model_1/model_feed_forward.pb")
//  predict(graph)

  def predict(graph: Graph) = {
    val s = new Session(graph)

    // Shape/dimension of input
    val inputDimension: Array[Long] = Array(1200).map(_.toLong)
    // Input array that conforms the dimension above
    val input = FloatBuffer.wrap(Array.range(0, 1200).map(_.toFloat))
    println(input)

    val x = Tensor.create(inputDimension, input)

    val y = s.runner().feed("joint_input", x).fetch("output_node0").run().get(0)
    System.out.println(y.floatValue())
  }

  def loadTfModel(source: String): Graph = {
    val graphDef = Files.readAllBytes(Paths.get(source))
    val graph = new Graph()
    graph.importGraphDef(graphDef)

    val ops = graph.operations().asScala.toList
    ops.foreach(println)
    println(graph)

    graph
  }
}
