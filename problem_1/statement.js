const plays = require("./data/plays.json");
const invoices = require("./data/invoices.json");

const format = new Intl.NumberFormat("en-US", { style: "currency", currency: "USD", minimumFractionDigits: 2 }).format;

function calculatePlayAmount(play, performance) {
  let thisAmount = 0;

  switch (play.type) {
    case "tragedy":
      thisAmount = 40000;
      if (performance.audience > 30) {
        thisAmount += 1000 * (performance.audience - 30);
      }
      break;
    case "comedy":
      thisAmount = 30000;
      if (performance.audience > 20) {
        thisAmount += 10000 + 500 * (performance.audience - 20);
      }
      thisAmount += 300 * performance.audience;
      break;
    default:
      throw new Error(`unknown type: ${play.type}`);
  }

  return thisAmount;
}

function calculateVolumeCredits(performance, play) {
  let credits = Math.max(performance.audience - 30, 0);
  if (play.type === "comedy") credits += Math.floor(performance.audience / 5);
  return credits;
}

function statement(invoice, plays) {
  let totalAmount = 0;
  let volumeCredits = 0;
  let result = `Statement for ${invoice.customer}\n`;

  for (let performance of invoice.performances) {
    const play = plays[performance.playID];
    let thisAmount = calculatePlayAmount(play, performance);
    volumeCredits += calculateVolumeCredits(performance, play);
    totalAmount += thisAmount;

    result += `  ${play.name}: ${format(thisAmount / 100)} (${performance.audience} seats)\n`;
  }

  result += `Amount owed is ${format(totalAmount / 100)}\n`;
  result += `You earned ${volumeCredits} credits\n`;

  return result;
}

console.log(statement(invoices[0], plays));