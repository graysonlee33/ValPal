const axios = require('axios');
const cheerio = require('cheerio');
const tableCrawler = require('./tableCrawler');
const _ = require("lodash");
const x = require('lodash.clonedeep');
const fs = require("fs");
const eventLinks = [];

const getEventLinks = async () => {
	try {
		const { data } = await axios.get(
			'https://www.vlr.gg/events'
		);
		const $ = cheerio.load(data);

		$('.events-container  > :nth-child(2) > a').each((_idx, el) => {
			var postTitle = $(el).attr('href');
			eventLinks.push(postTitle);
		});

		return eventLinks;
	} catch (error) {
		throw error;
	}
};

const agents = [["astra", 0], ["breach", 0], ["brimstone", 0], ["chamber", 0], ["cypher", 0], ["fade", 0], ["harbor", 0], ["jett", 0], ["kayo", 0], ["killjoy", 0], ["neon", 0], ["omen", 0], ["phoenix", 0], ["raze", 0], ["reyna", 0], ["sage", 0], ["skye", 0], ["sova", 0], ["viper", 0], ["yoru", 0]];
const maps = ["Pearl", "Fracture", "Breeze", "Icebox", "Bind", "Haven", "Ascent"];
var mapCount = [0, 0, 0, 0, 0, 0, 0];
const finalAgentPercentages = [];
for (let e = 0; e < maps.length; e++) {
	var arr = _.cloneDeep(agents);
	var temp = [maps[e], arr];
	finalAgentPercentages.push(temp);
}


getEventLinks().then(function(eventLinks) {

	for (let e = 0; e < eventLinks.length; e++) {
		var x = (eventLinks[e]).substring(6);
		var link = "https://www.vlr.gg/event/agents".concat(x);

		tableCrawler.tableScraper(link).then(function(finalOne) {
			for (let e = 0; e < finalOne.length; e++) {
					for (let f = 0; f < finalAgentPercentages.length; f++) {
						if (finalOne[e][0] === finalAgentPercentages[f][0]) {
							mapCount[f] += 1;
							  for (let z = 0; z < agents.length; z++) {
								finalAgentPercentages[f][1][z][1] += finalOne[e][1][z][1];
							  }
						}
					
					}
			}
			 if(e == (eventLinks.length-1))
			{
				var results = "";
				  for (let e = 0; e < finalAgentPercentages.length; e++) {
					//console.log(finalAgentPercentages[e][0] + "\n");
					results += finalAgentPercentages[e][0] + "\n";
					for (let f = 0; f < agents.length; f++) {
						var computedPercentage = (finalAgentPercentages[e][1][f][1]/mapCount[e]);
						//console.log(finalAgentPercentages[e][1][f][0] + ": " + computedPercentage + "\n");
						results += finalAgentPercentages[e][1][f][0] + ": " + computedPercentage + "\n";
					}
					//console.log("\n");
					results += "\n";
				}
				console.log(results);
				fs.writeFile("ProAgentPercentages.txt", results, err => {
					if (err) throw err;
					console.log('File successfully written to disk');
			   })
			}
			
		});
	} 
	
});



