/////// V1 ////////
function drawChart() {
    
    var margin = { top: 20, right: 20, bottom: 100, left: 50 },
        width = 960 - margin.left - margin.right,
        height = 500 - margin.top - margin.bottom;
    
    // set the ranges
    var x = d3.scaleTime().range([0, width]);
    var y = d3.scaleLinear().range([height, 0]);

    // define the line
    var valueline = d3.line()
        .x(function (d) { return x(d.date); })
        .y(function (d) { return y(d.value); });

    var svg = d3.select("#chart").append("svg")
        .attr("width", width + margin.left + margin.right)
        .attr("height", height + margin.top + margin.bottom)
        .append("g")
        .attr("transform",
            "translate(" + margin.left + "," + margin.top + ")");

    function chart(data) {
        // Scale the range of the data
        x.domain(d3.extent(data, function (d) { return d.date; }));
        y.domain(d3.extent(data, function (d) { return d.value; }));

        // Add the valueline path.
        svg.append("path")
            .data([data])
            .attr("class", "line")
            .attr("d", valueline)

        // Add the X Axis
        svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate(0," + height + ")")
            .call(d3.axisBottom(x)
            .tickFormat(d3.timeFormat("%d-%m-%Y")))
            .selectAll("text")
            .style("text-anchor", "end")
            .attr("dx", "-.8em")
            .attr("dy", ".15em")
            .attr("transform", "rotate(-45)");

        // Add the Y Axis
        svg.append("g")
            .attr("class", "axis")
            .call(d3.axisLeft(y));
        
        svg
        .selectAll("dot")
        .data(data)
        .enter()
        .append("circle")
            .attr("class", "circle")
            .attr("cx", function(d){return x(d.date)}) 
            .attr("cy", function(d){return y(d.value)})
            .attr("r", 7)
            .on("mouseover", mouseover)
            .on("mousemove", mousemove)
            .on("mouseleave", mouseleave)
        

    }
    
    // create a tooltip
    var Tooltip = d3.select("#chart")
      .append("span")
      .style("opacity", 0)
      .attr("class", "tooltip")
      .style("background-color", "white")
      .style("border", "solid")
      .style("border-color", "red")
      .style("border-width", "2px")
      .style("border-radius", "5px")
      .style("padding", "5px")
      .style("position", "absolute")
      .style("z-index", "1")
    
     // Three function that change the tooltip when user hover / move / leave a cell
     var mouseover = function(d) {
         Tooltip
             .style("opacity", 1)
      }
     var mousemove = function(d) {
         Tooltip
             .html("Exact value: " + d.value)
             .style("left", (d3.mouse(this)[0]+70) + "px")
             .style("top", (d3.mouse(this)[1]) + "px")
      }
     var mouseleave = function(d) {
         Tooltip
             .style("opacity", 0)
      }

    return chart;


}


/////// V1 ////////

