# 2024F CS2910 Project Report
**Group Members**
1. Priestly Ogbodo (0357910)
2. Yuusuf Adebayo (0363297)

# Summary of Game
_A summary description of the game that you chose.  This should include the name of the game, 
the goal of the game and a brief description of the key objects in the game. Please provide a link to the game and/or its rules online. (100 words)_

**Monopoly:**
The game we chose for the project is Monopoly, a classic board game where players compete to dominate the market by acquiring properties, building houses and managing their finances strategically. 
The goal of the game is to bankrupt all opponents and become the wealthiest player. 
Key objects in the game include the board (divided into property spaces, utilities, railroads, and special spaces like ("Go to Jail", "GO", "Free Parking"), Income Tax, property cards, chance and community chest cards, houses. 
Players earn rent from owned properties and face various financial challenges. Official rules can be found here: https://www.officialgamerules.org/board-games/monopoly


# Experiment Report
## Player Strategies
_For each of 3 strategies you implemented, name the strategy, and then description of it (100 words each)_

**1.Aggressive Strategy:**
**Description:**
Aggressive players prioritize rapid expansion by acquiring properties and houses whenever possible.
**Behavior:**
They buy any property they can afford, regardless of their remaining cash.
For houses, they aim to build up to three at a time if funds allow.
However, it risks financial instability due to insufficient cash reserves for contingencies like fines or taxes.

**2.Conservative Strategy:**
**Description:**
Conservative players adopt a risk-averse approach, prioritizing financial security.
**Behavior:**
They purchase properties only if their remaining cash exceeds a 500 buffer after accounting for fines or bail.
When constructing houses, they build one at a time, ensuring that their cash reserves remain above 500.
This strategy aims for steady growth while minimizing the risk of bankruptcy. It sacrifices early expansion for long-term survivability.

**3.Random Strategy:**
**Description:**
Random players make decisions based on chance rather than calculated risks or goals.
**Behavior:**
They purchase properties with a 50% probability, irrespective of their financial status.
Similarly, their house-building decisions are randomized, choosing to build either one or three houses if funds permit.
This strategy introduces unpredictability, leading to varied outcomes that depend heavily on external game events and random luck.


## Procedure
_A description of your procedure for running the experiment on your code. This should include information 
regarding the setup of the experiment in terms of what it runs and how it compares the player strategies, 
the number of trials the experiment and what data was collected. (250 words)_
The experiment aimed to evaluate the effectiveness of three strategies — Aggressive, Conservative, and Random in a simulated board game setting.
Sixty games were conducted, each involving three players, with one player assigned to each strategy.
The simulation framework adhered to simplified game rules, focusing on property acquisition, house-building, and financial management, while ensuring consistent initial conditions across all trials.

At the start of each game, players were initialized with equal cash reserves and no properties. 
During each turn, players made decisions regarding property purchases and house-building based on their respective strategies. 
For example, aggressive players prioritized rapid expansion, conservative players made cautious investments, and random players operated without structured planning. 
The game continued until all but one player were eliminated.

**Throughout each simulation, key metrics were recorded:**
- The winning strategy. 
- The total number of times each strategy wins. 
- The number of times it's a draw - "When the maximum turns of 500 is reached, The Game is a Draw!!"
- Total cash reserves and properties owned by each strategy at the end of the game. 
- The average total number of turns taken to conclude the game

These metrics provided insights into both the short-term and long-term impacts of the different strategies. 
After completing the 60 simulations, the data was aggregated to identify patterns and trends in player performance. 
This systematic approach enabled a robust comparison of the three strategies under diverse game scenarios. 
The findings highlighted the trade-offs between risk and reward in strategic decision-making, providing a clear understanding of the strengths and weaknesses of each strategy.


## Results
_A presentation on the results of your simulation of the strategies in table(s) or appropriate graphic(s) 
with a short summary. (250 words)_

**Introduction**
The simulation analyzed the performance of three strategies—Conservative, Aggressive, and Random—over six sets of games. Each set simulated 60 games, recording metrics such as wins, average money collected, average properties owned, and the number of turns.

**Key Findings**
**Wins:**
The Conservative strategy dominated across all simulations, winning between 38 to 52 games per set.
The Aggressive strategy showed moderate performance, with wins ranging from 6 to 14.
The Random strategy was the least successful, achieving only 1 to 7 wins.

**Average Money Collected:**
Conservative players collected the most money, averaging between 26,738 to 34,286 per simulation.
Aggressive players collected far less, ranging from 2,785 to 7,619.
Random players performed poorly, with averages as low as 96 in one simulation.

**Average Properties Owned:**
Conservative players consistently owned the most properties, with averages between 34 and 52.
Aggressive players owned fewer properties, ranging from 11 to 29.
Random players trailed, averaging 4 to 22 properties.

**Game Duration:**
Games where Conservative strategies dominated tended to have longer durations, averaging 38.93 to 52.95 turns.

**Summary**
The Conservative strategy outperformed the other two in every key metric. Its focus on steady resource accumulation, strategic property acquisition, and risk minimization made it the most effective. 
Aggressive and Random strategies, while occasionally successful, lacked the consistency to compete effectively. These results highlight the importance of planning and calculated decision-making in competitive scenarios.

**Image of simulations:**
![average money collected.png](average%20money%20collected.png)
![average properties owned.png](average%20properties%20owned.png)
![wins by strategy.png](wins%20by%20strategy.png)

## Analysis
_An interpretation of your data explaining why one strategy is better than the other supported with 
data from your experiment.(500 words)_

**Dominance in Win Rate:**
In every simulation set, the Conservative strategy achieved the highest number of wins, ranging from 38 to 52 wins out of 60 games.
This consistent winning performance highlights its resilience and effectiveness over different game dynamics.
For example:
In one simulation, the Conservative strategy won 52 games compared to just 6 by Aggressive and 2 by Random.
Even in the least favorable scenario, it secured 38 wins, maintaining a substantial lead over the other strategies.
This dominance suggests that the Conservative strategy is highly optimized for securing victories regardless of variations in other metrics.

**Higher Average Money Collected:**
The amount of money collected is a crucial factor in determining success in these games, as it reflects a strategy's ability to generate and manage resources effectively. 
The Conservative strategy consistently outperformed the others in this metric, with average money collected ranging from 26,738 to 34,286. 
In comparison:
The Aggressive strategy averaged between 2,785 and 7,619, often less than a quarter of what Conservative players earned.
The Random strategy lagged even further behind, averaging as little as 96 in one simulation.
The Conservative approach focuses on steady and sustained financial growth, enabling players to make calculated investments and stay competitive throughout the game. 
This financial advantage translates directly into higher win rates.

**Superiority in Property Ownership:**
Property ownership is another critical metric, as properties generate consistent revenue and provide strategic leverage. Conservative players owned significantly more properties on average in all simulations:
The Conservative strategy averaged between 34 and 52 properties per game.
Aggressive players managed between 11 and 29 properties, while Random players owned only 4 to 22 properties.
This consistent control over properties suggests that the Conservative strategy emphasizes strategic acquisitions and careful resource allocation, avoiding overextension or reckless spending that could jeopardize long-term success.
  
**Steady Turn Lengths:**
The Conservative strategy also leads to games with a higher average number of turns, indicating a focus on long-term planning. 
For instance, the average turns per game ranged from 38.93 to 52.95 when this strategy dominated. Longer games align with the Conservative strategy's emphasis on resource accumulation and property development, which take time to pay off.

**Why Conservative Works:**
The Conservative strategy excels because it balances risk and reward. By prioritizing financial stability and incremental growth, players can:
Accumulate resources, Secure a strong economic foundation.
Make strategic investments: Acquire properties methodically, ensuring consistent revenue streams.
Avoid risks: Minimize losses by avoiding overly aggressive moves or random decision-making.
In contrast, the Aggressive strategy, while occasionally successful, often overextends players, leading to financial collapse or insufficient resources to recover from setbacks. 
The Random strategy's lack of direction inherently limits its ability to compete effectively.
  
**Conclusion**
The simulation data strongly supports the conclusion that the Conservative strategy is superior in this context. Its focus on steady growth, careful resource management, and strategic property acquisition allows it to dominate across win rates, money collected, and properties owned. 
In contrast, the Aggressive and Random strategies struggle to maintain the same level of consistency and long-term viability.


# Reflection
_A reflection on your experiences with generative AI during this project. Provide a few sentences reflecting
on your experience with AI for each of the following prompts._ 

### What generative AI did you use, and what tasks did you use it for?
_**Example:** Git Co-Pilot: generated getters and setters_

### How did you learn about the tools used by your group (delete ones that don't apply)?
_Please describe where and how you learned about the tools_

### Reflecting on your experience:
_Write a short reflection on your use of generative AI in this project, including if you did not use it. 
You may use the prompts as headings if you wish. (500 words)_  

**Reflection on Our Generative AI Usage**
One of the biggest perks of using generative AI(Co-pilot and ChatGpt) was how it streamlined the initial design phase. By turning abstract ideas into actionable code snippets, AI made it possible for us to quickly create the foundational components of the simulation.
For instance, defining player strategies and structuring the experiment was much faster with AI's help. Instead of starting from scratch, We used AI-generated templates as a strong baseline for further customization.
AI was also excellent at suggesting improvements to existing code. When defining player behaviors for strategies like "Aggressive," "Conservative," and "Random," AI offered intuitive decision-making patterns that fit the goals of each strategy which we wanted.
These suggestions saved us time and ensured the strategies were robust and aligned with the game rules we implemented.

Another area where AI helped was Creating failing tests, troubleshooting, creation of methods - "Which we could not implement ourselves" ,debugging and also used it to simplify some code by using methods like streams in the project which have not being taught. For example, when metrics like the "Number of times a strategy wins" were calculated inaccurately, AI provided systematic suggestions to fix the issues. 
It also helped identify potential errors in data aggregation - "Combined multiple simulation results", ensuring the experiment produced accurate and reliable results.
AI's role extended beyond coding. It provided insightful frameworks for interpreting simulation outcomes, helping us articulate the trade-offs between strategies. 
For example, it highlighted the aggressive strategy’s high-risk, high-reward nature compared to the conservative strategy's consistent but slower growth. These insights were crucial in crafting a compelling narrative around the results.
    
However, using AI wasn't without its challenges, as errors were found and debugged by us. Sometimes, the suggestions were too generic or didn't align with the project's specific requirements. 
For example, when generating code for advanced simulations, AI sometimes overlooked nuances in the game logic, requiring careful manual intervention to ensure accuracy. Similarly, while AI was great at generating visualizations, its suggestions weren't always optimized for the specific libraries or frameworks I was using, necessitating additional adjustments.
Another limitation was AI's lack of context regarding the project's unique objectives. While it excelled at generating code structures, integrating them into a cohesive system often required a deeper understanding that AI lacked. 
This meant that, while AI could help construct individual components, it was up to us to ensure they worked together seamlessly.
Despite these limitations, AI was an invaluable tool for brainstorming and implementation support. It significantly reduced the time spent on repetitive tasks, allowing me to focus on higher-level design and analysis. Its ability to quickly adapt to new prompts and provide diverse perspectives made it an excellent companion for creative problem-solving.
    
In conclusion, generative AI enhanced my productivity, improved code quality, and offered valuable insights throughout the project. 
While it occasionally required manual refinement, the overall experience highlighted its potential as a powerful aid in software development and experimentation. With thoughtful integration, AI can be an indispensable resource for tackling complex projects efficiently and effectively.


**Prompts to think about in writing your reflections if you worked with generative AI:**
- What went well using generative AI in this project?
- What went well using generative AI?
- What didn’t go well using generative AI?
- Were there any limitations you encountered using generative AI?
- How did your solution change/evolve/improve/degrade because of the generative AI?
- What could you have done so the project turned out better?

**Prompts to think about in writing your reflections if you didn't use generative AI:**
- Why did you choose not to use generative AI in this project?
- Were there particular problems that you encountered where you think generative AI would have helped?
- Were there particular things you were glad you learned to do yourself without the use of generative AI?
- What could you have done so the project would turn out better?

# Bonus Consideration:
If you have aspects of your project you would like considered for the available bonus.
- The use of proper OOP concepts
- Advanced Analysis and Visualization
- Well Sophisticated Strategies
- Robustness Testing
- Custom Game Modifications
