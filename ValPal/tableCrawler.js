
const axios = require("axios");
const cheerio = require("cheerio");
const _ = require("lodash");
const x = require('lodash.clonedeep');
const ObjectsToCsv = require("objects-to-csv");



async function html_scraper(link) {
    const response = await axios(link);
    
    const html = await response.data;
    const $ = cheerio.load(html);

    const allRows = $('table.wf-table.mod-pr-global > tbody > tr');

    var agentNames = [];
    const finalOne = [];
    
    var firstRow = true;
    var count = 0;
    
    allRows.each((index, element) => 
    {
        if (firstRow) 
        {
            const img = $(element).find('th').find('img');


            for (let e = 0; e < img.length; e++) {
                var agent = [($(img[e]).attr('src')).substring(21, ($(img[e]).attr('src')).indexOf('.'))];
                agentNames.push(agent);
            }
            firstRow = false;
        }
        else if(index > 1)
        {
            const tdMap = $(element).find('td');
            var name = $(tdMap[0]).text();
            var cleanedMap = (name.trim()).substring(((name.trim()).lastIndexOf("\n"))).trim();
            var arr = _.cloneDeep(agentNames);
            var temp = [cleanedMap, arr];
            finalOne.push(temp);
            
            
            const span = $(element).find('td').find('span');
            for (let e = 1; e < span.length; e++) {
                var agentPercentage = ($(span[e]).text()).trim();
                var convertedAgentPercentage = parseInt(agentPercentage.substring(0, (agentPercentage.length - 1)));
                finalOne[count][1][e-1].push(convertedAgentPercentage);
                
            }
            count += 1;
        }
    })
    for (let e = 0; e < finalOne.length; e++) {
        for(var i = 0; i < finalOne[e][1].length; i++){

            for(var j = 0; j < (finalOne[e][1].length - i -1 ); j++){
                
              if(finalOne[e][1][j][0] > finalOne[e][1][j+1][0]){

                var temp = finalOne[e][1][j];
                finalOne[e][1][j] = finalOne[e][1][j+1];
                finalOne[e][1][j+1] = temp;
              }
            }
          }
        
    }

    return finalOne;

};

module.exports.tableScraper = html_scraper;